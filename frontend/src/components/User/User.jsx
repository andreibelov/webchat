import React, { Component } from 'react';


class User extends Component {
    render() {
        return (
        <tr>
            <td>{this.props.user.firstName}</td>
            <td>{this.props.user.secondName}</td>
            <td>{this.props.user.email}</td>
        </tr>);
    }
}

export default User;