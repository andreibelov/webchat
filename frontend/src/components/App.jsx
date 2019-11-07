import './App.scss';
import React, { Component } from 'react';
import UserTable from "./User/UserTable";

const client = require('../api/client');

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {users: []};
    }

    componentDidMount() {
        client({method: 'GET', path: '/api/v2/users'}).done(response => {
            this.setState({users: response.entity});
        });
    }

    render() {
        return (<UserTable users={this.state.users}/>);
    }
}

export default App;
