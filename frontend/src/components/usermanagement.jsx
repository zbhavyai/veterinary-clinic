import React, { Component } from 'react';

import { getUsers } from './../services/fakeUserService';

class UserManagement extends React.Component {
    state = {
        users: getUsers(),
        filterOption: 0,
        filterText: "",
        pageSize: 4
    };

    handleFilter =(e)=>{
        this.setState({filterOption:e.target.value});
        //this.setState({filterOption: 1});
        console.log("ID clicked", this.state.filterOption);

    };

    handleFilterText =(e)=>{
        this.setState({filterText:e.target.value});
        //this.setState({filterOption: 1});
        console.log("tedt changed", this.state.filterText);

    };



    render() { 
        let filtered = this.state.users;
        if (this.state.filterOption == 1) {
            filtered = 1?this.state.users.filter(m=>m["u_userid"].toString().toLowerCase().includes(this.state.filterText.toLowerCase()) ):this.state.users;
          } else if (this.state.filterOption == 2) {
            filtered = 1?this.state.users.filter(m=>m["u_firstname"].toString().toLowerCase().includes(this.state.filterText.toLowerCase()) ):this.state.users;
          } else if (this.state.filterOption == 3) {
            filtered = 1?this.state.users.filter(m=>m["u_lastname"].toString().toLowerCase().includes(this.state.filterText.toLowerCase()) ):this.state.users;
          } else if (this.state.filterOption == 4) {
            filtered = 1?this.state.users.filter(m=>m["u_role"].toString().toLowerCase().includes(this.state.filterText.toLowerCase())):this.state.users;
          } else if (this.state.filterOption == 5) {
            filtered = 1?this.state.users.filter(m=>m["u_email"].toString().toLowerCase().includes(this.state.filterText.toLowerCase()) ):this.state.users;
        } else if (this.state.filterOption == 6) {
            filtered = 1?this.state.users.filter(m=>m["status"].toString().toLowerCase().includes(this.state.filterText.toLowerCase()) ):this.state.users;
          } else{
            filtered = this.state.users;
          }
        return <React.Fragment>
            <div class="container">
                  
                  </div>
              
              
             
                  
                  
                  <div className="input-group mb-3">
                      
                      <label className="input-group-text" htmlFor ="inputGroupSelect01">Search For</label>
                      <select   onChange = {(e) => this.handleFilter(e)} value = {this.state.filterOption}  className="form-select" id="selectFilter" >
                          <option value = "0">No Filter</option>
                          <option value="1">ID</option>
                          <option value="2">First Name</option>
                          <option value="3">Last Name</option>
                          <option value="4">Role</option>
                          <option value="5">Email</option>
                          <option value="5">Status</option>
                      </select>
                  </div>
  
                  <div className="input-group mb-3">
                      < input type="text" id="inputFilter" onChange = {(e) => this.handleFilterText(e)} value = {this.state.filterText} className="form-control" placeholder="Enter Your Search Term Here" aria-label="Enter Your Search Term Here" aria-describedby="basic-addon2" />
                      
                  </div>
          
          <table className="table">
              <thead>
                  <tr>
                      <th>ID</th>
                      <th>First Name</th>
                      <th>Last Name</th>
                      <th>Role</th>
                      <th>Email</th>
                      <th>Status</th>
                      <th></th>
                  </tr>
              </thead>
              <tbody>
                  {filtered.map(user =>(
                      <tr key = {user["u_userid"].toString()}>
                      <td>{user["u_userid"].toString()}</td>
                      <td>{(user["u_firstname"]==null) ? 'na' : user["u_firstname"].toString()}</td>
                      <td>{(user["u_lastname"]==null) ? 'na' : user["u_lastname"].toString()}</td>
                      <td>{(user["u_role"]==null) ? 'na' : user["u_role"].toString()}</td>
                      <td>{(user["u_email"]==null) ? 'na' : user["u_email"].toString()}</td>
                      <td>{(user["status"]==null) ? 'na' : user["status"].toString()}</td>
                      <td><button  className="btn btn-danger btn-sm">Details</button></td>
                      
                      </tr>
  
                  ))}
              
  
                  
                  
              </tbody>
          </table>
          <div class="row">
                        <div class="col-sm">
                          
                        </div>
                        <div class="col-sm">
                          
                        </div>
                        <div class="col-sm">
                          
                        </div>
                        <div class="col-sm">
                          
                        </div>
                        <div class="col-sm">
                          <button type="button" class="btn btn-secondary">Add User</button>
                        </div>
                        
          </div>

        </React.Fragment>;
    }
}
 
export default UserManagement;