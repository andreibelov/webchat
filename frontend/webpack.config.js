const path = require('path');
const webpack = require('webpack');

const autoprefixer = require('autoprefixer');
const packageJSON = require('./package.json');
const InterpolateHtmlPlugin = require('react-dev-utils/InterpolateHtmlPlugin');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const ExtractTextPlugin = require('extract-text-webpack-plugin');
const ManifestPlugin = require('webpack-manifest-plugin');


// Evaluating variables
const fs = require('fs');
const resolveApp = relativePath => path.resolve(fs.realpathSync(process.cwd()), relativePath);

const PATHS = {
    build: path.join(resolveApp('out/resources/webjars'), packageJSON.name, packageJSON.version),
    publicPath: '',
    filename: 'app-bundle.js',
    appHtml: resolveApp('static/index.html'),
    appIcon: resolveApp('static/favicon.ico'),
    appSrc: resolveApp('src'),
    publicUrl: '',
    staticContent: resolveApp('static')
};

// Grab NODE_ENV and REACT_APP_* environment variables and prepare them to be
// injected into the application via DefinePlugin in Webpack configuration.
const REACT_APP = /^REACT_APP_/i;
// Get environment variables to inject into our app.
const env = {
    raw: Object.keys(process.env)
        .filter(key => REACT_APP.test(key))
        .reduce(
            (env, key) => {
                env[key] = process.env[key];
                return env;
            },
            {
                // Useful for determining whether we’re running in production mode.
                // Most importantly, it switches React into the correct mode.
                NODE_ENV: process.env.NODE_ENV || 'development',
                // Useful for resolving the correct path to static assets in `public`.
                // For example, <img src={process.env.PUBLIC_URL + '/img/logo.png'} />.
                // This should only be used as an escape hatch. Normally you would put
                // images into the `src` and `import` them in code to get their paths.
                PUBLIC_URL: PATHS.publicUrl,
            })
};

module.exports = {
    // Don't attempt to continue if there are any errors.
    bail: true,
    context: __dirname,
    devtool: 'source-map',
    entry: [
        // Include an alternative client for WebpackDevServer. A client's job is to
        // connect to WebpackDevServer by a socket and get notified about changes.
        // When you save a file, the client will either apply hot updates (in case
        // of CSS changes), or refresh the page (in case of JS changes). When you
        // make a syntax error, this client will display a syntax error overlay.
        // Note: instead of the default WebpackDevServer client, we use a custom one
        // to bring better experience for Create React App users. You can replace
        // the line below with these two lines if you prefer the stock client:
        // require.resolve('webpack-dev-server/client') + '?/',
        // require.resolve('webpack/hot/dev-server'),
        require.resolve('react-dev-utils/webpackHotDevClient'),
        // Finally, this is your app's code:
        './src/index.js'
    ],
    output: {
        // Add /* filename */ comments to generated require()s in the output.
        pathinfo: true,
        path: PATHS.build,
        publicPath: PATHS.publicPath,
        filename: PATHS.filename
    },
    resolve: {
        // These are the reasonable defaults supported by the Node ecosystem.
        // We also include JSX as a common component filename extension to support
        // some tools, although we do not recommend using it, see:
        // https://github.com/facebookincubator/create-react-app/issues/290
        // `web` extension prefixes have been added for better support
        // for React Native Web.
        extensions: ['.web.js', '.mjs', '.js', '.json', '.web.jsx', '.jsx'],
        alias: {

            // Support React Native Web
            // https://www.smashingmagazine.com/2016/08/a-glimpse-into-the-future-with-react-native-for-web/
            'react-native': 'react-native-web',
        }
    },
    module: {
        rules: [
            {
                test: /\.jsx?$/,
                loaders: ['babel-loader?presets[]=es2015&presets[]=react'],
                include: PATHS.appSrc,
                // query: {
                //     cacheDirectory: true,
                //     presets: ['react', 'es2015']
                // }
            },
            {
                test: /\.json$/,
                loader: 'json-loader',
                include: PATHS.staticContent
            },
            {
                test: /\.(css|scss)$/,
                use: ExtractTextPlugin.extract({
                    fallback: 'style-loader',
                    use: [
                        {
                            loader: 'css-loader',
                            options: {
                                importLoaders: 2
                            }
                        },
                        'sass-loader',
                        {
                            loader: require.resolve('postcss-loader'),
                            options: {
                                ident: 'postcss',
                                plugins: () => [
                                    require('postcss-flexbugs-fixes'),
                                    autoprefixer({
                                        browsers: [
                                            '>1%',
                                            'last 4 versions',
                                        ],
                                    }),
                                ],
                            }
                        }
                    ]
                })
            },
            {
                test: /\.less$/,
                loader: 'style!css!less'
            },
            {
                test: /\.jpe?g$|\.ico$|\.gif$|\.png$|\.svg$|\.wav$|\.mp3$/,
                loader: 'file-loader?name=[name].[ext]',  // <-- retain original file name
                include: PATHS.staticContent
            },
            // Needed for the css-loader when [bootstrap-webpack](https://github.com/bline/bootstrap-webpack)
            // loads bootstrap's css.
            {test: /\.eot(\?v=\d+\.\d+\.\d+)?$/, loader: 'file-loader'},
            {test: /\.svg(\?v=\d+\.\d+\.\d+)?$/, loader: 'url-loader?limit=10000&mimetype=image/svg+xml'},
            {test: /\.ttf(\?v=\d+\.\d+\.\d+)?$/, loader: 'url-loader?limit=10000&mimetype=application/octet-stream'},
            {test: /\.(woff|woff2)(\?v=\d+\.\d+\.\d+)?$/, loader: 'url-loader?limit=10000&mimetype=application/font-woff'}
        ]
    },
    plugins: [
        // Makes some environment variables available in index.html.
        // The public URL is available as %PUBLIC_URL% in index.html, e.g.:
        // <link rel="shortcut icon" href="%PUBLIC_URL%/favicon.ico">
        // In development, this will be an empty string.
        new InterpolateHtmlPlugin(env.raw),
        // Generates an `index.html` file with the <script> injected.
        new HtmlWebpackPlugin({
            inject: true,
            favicon: PATHS.appIcon,
            template: PATHS.appHtml,
        }),
        new ExtractTextPlugin({
            filename: '[name].style.css',
            allChunks: true
        }),
        new ManifestPlugin({
            publicPath: PATHS.publicPath,
            seed: JSON.parse(fs.readFileSync(
                PATHS.staticContent + '/manifest.json',
                "utf8")
            )
        }),
        new webpack.NoEmitOnErrorsPlugin(),
        // Add module names to factory functions so they appear in browser profiler.
        new webpack.NamedModulesPlugin(),
        // This is necessary to emit hot updates (currently CSS only):
        new webpack.HotModuleReplacementPlugin()
    ],
    //https://webpack.js.org/configuration/dev-server/
    devServer: {
        open: true,
        host: '127.0.0.1',
        port: 5050,
        headers: {
            'X-Custom-Foo': 'bar'
        },
        proxy: {
            '/api': {
                target: 'http://localhost:8080/api',
                changeOrigin: true,
                pathRewrite: {
                    '^/api': ''
                }
            }
        }
    }
};
