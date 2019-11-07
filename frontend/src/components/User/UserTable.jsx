import React, {Component} from 'react';
import User from "./User.jsx";

import {Table, Divider} from 'antd';

const {Column, ColumnGroup} = Table;

// rowSelection object indicates the need for row selection
const rowSelection = {
    onChange: (selectedRowKeys, selectedRows) => {
        console.log(`selectedRowKeys: ${selectedRowKeys}`);
        console.log('selectedRows: ', JSON.stringify(selectedRows));
    },
    getCheckboxProps: record => ({
        disabled: record.email === 'test@test.test', // Column configuration not to be checked
        name: record.email,
    }),
};

class UserTable extends Component {
    render() {
        return (
            <Table
                rowKey="id"
                rowSelection={rowSelection}
                dataSource={this.props.users}>
                <Column title="Name" key="name"
                        render={(text, record) => (
                        <span>
                          {record.firstName} {record.lastName}
                        </span>
                        )}
                />
                <Column title="Role" dataIndex="role" key="role"/>
                <Column title="Email Address" dataIndex="email" key="email"/>
                <Column
                    title="Action"
                    key="action"
                    render={(text, record) => (
                        <span>
                          <a>Invite {record.lastName}</a>
                          <Divider type="vertical"/>
                          <a>Delete</a>
                        </span>
                    )}
                />
            </Table>);
    }
}

export default UserTable;