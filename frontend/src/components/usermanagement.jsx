import React from 'react';
import axios from 'axios';
import NavBar from './navbar';
import {
    
    Link
} from "react-router-dom";


class UserManagement extends React.Component {
    state = {
        users: [],
        // users:getUsers(),
        filterOption: 0,
        filterText: "",
        pageSize: 4
    };

    handleFilter = (e) => {
        this.setState({ filterOption: e.target.value });
        //this.setState({filterOption: 1});
        console.log("ID clicked", this.state.filterOption);

    };

    handleFilterText = (e) => {
        this.setState({ filterText: e.target.value });
        //this.setState({filterOption: 1});
        console.log("tedt changed", this.state.filterText);

    };

    handleRemove =(e, user)=>{
        
        
        
        
        const link = "http://localhost:8080/api/v1/users/" + user["userId"];
        axios.delete(link).then(res => {
            console.log(res);
            console.log(res.data);
          });;

          setTimeout(() => {
            window.location.reload(false);
         }, 500);
        //window.location.reload(false);

        


    };

    async componentDidMount() {
        const { data: users } = await axios.get('http://localhost:8080/api/v1/users/', { headers: { 'Access-Control-Allow-Origin': true } });
        this.setState({ users });

        //const promise = axios.get('https://jsonplaceholder.typicode.com/posts')
    }



    render() {
        console.log(this.state.users);
        const userx = this.props.match.params.user;
        const uid = this.props.match.params.uid;
        let filtered = this.state.users;
        if(userx == "i"){
            filtered = 1?this.state.users.filter(m=>m["role"].toString().includes("STUDENT") ):this.state.users;
        }
        

        if (this.state.filterOption == 1) {
            filtered = 1 ? this.state.users.filter(m => m["userId"].toString().toLowerCase().includes(this.state.filterText.toLowerCase())) : this.state.users;
        } else if (this.state.filterOption == 2) {
            filtered = 1 ? this.state.users.filter(m => m["firstName"].toString().toLowerCase().includes(this.state.filterText.toLowerCase())) : this.state.users;
        } else if (this.state.filterOption == 3) {
            filtered = 1 ? this.state.users.filter(m => m["lastName"].toString().toLowerCase().includes(this.state.filterText.toLowerCase())) : this.state.users;
        } else if (this.state.filterOption == 4) {
            filtered = 1 ? this.state.users.filter(m => m["role"].toString().toLowerCase().includes(this.state.filterText.toLowerCase())) : this.state.users;
        } else if (this.state.filterOption == 5) {
            filtered = 1 ? this.state.users.filter(m => m["emailId"].toString().toLowerCase().includes(this.state.filterText.toLowerCase())) : this.state.users;
        } else if (this.state.filterOption == 6) {
            filtered = 1 ? this.state.users.filter(m => m["status"].toString().toLowerCase().includes(this.state.filterText.toLowerCase())) : this.state.users;
        } else {
            filtered = this.state.users;
            if(userx == "i"){
                filtered = 1?this.state.users.filter(m=>m["role"].toString().includes("STUDENT") ):this.state.users;
            }

        }

        
        if(userx == "i"){
            return <React.Fragment>
            <NavBar user = {userx} uid = {uid}/>

            <div className="container">
            </div>

            <div className="input-group mb-3">
                <label className="input-group-text" htmlFor="inputGroupSelect01">Search For</label>
                <select onChange={(e) => this.handleFilter(e)} value={this.state.filterOption} className="form-select" id="selectFilter" >
                    <option value="0">No Filter</option>
                    <option value="1">ID</option>
                    <option value="2">First Name</option>
                    <option value="3">Last Name</option>
                    <option value="4">Role</option>
                    <option value="5">Email</option>
                    <option value="5">Status</option>
                </select>
            </div>

            <div className="input-group mb-3">
                < input type="text" id="inputFilter" onChange={(e) => this.handleFilterText(e)} value={this.state.filterText} className="form-control" placeholder="Enter Your Search Term Here" aria-label="Enter Your Search Term Here" aria-describedby="basic-addon2" />
            </div>

            <table className="table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Role</th>
                        <th>Joining Date</th>
                        <th>Status</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    {filtered.map(user => (
                        <tr key={user["userId"].toString()}>
                            <td>{user["userId"].toString()}</td>
                            <td>{(user["fullName"] == null) ? 'na' : user["fullName"].toString()}</td>
                            <td>{(user["emailId"] == null) ? 'na' : user["emailId"].toString()}</td>
                            <td>{(user["role"] == null) ? 'na' : user["role"].toString()}</td>
                            <td>{(user["joiningDate"] == null) ? 'na' : user["joiningDate"].toString()}</td>
                            <td>{(user["status"] == null) ? 'na' : user["status"].toString()}</td>

                            {/* <td><Link to={"/" + userx + "/users/" + user["userId"].toString()} className="btn btn-primary btn-sm">Details</Link></td> */}
                            <td><Link to={"/"+userx + "/" + uid + "/users/"  + user["userId"].toString()} className="btn btn-primary btn-sm">Details</Link></td>
                        </tr>
                    ))}
                </tbody>
            </table>

            <div className="row">
                <div className="col-sm">
                </div>

                <div className="col-sm">
                </div>

                <div className="col-sm">
                </div>

                <div className="col-sm">
                </div>

                <div className="col-sm">
                <Link to={"/"+userx + "/" + uid +  "/users/add" } className="btn btn-secondary">Add User</Link>
                </div>
            </div>

        </React.Fragment>;

        } 
        else{
            return <React.Fragment>
            <NavBar user = {userx} uid = {uid}/>

            <div className="container">
            </div>

            <div className="input-group mb-3">
                <label className="input-group-text" htmlFor="inputGroupSelect01">Search For</label>
                <select onChange={(e) => this.handleFilter(e)} value={this.state.filterOption} className="form-select" id="selectFilter" >
                    <option value="0">No Filter</option>
                    <option value="1">ID</option>
                    <option value="2">First Name</option>
                    <option value="3">Last Name</option>
                    <option value="4">Role</option>
                    <option value="5">Email</option>
                    <option value="5">Status</option>
                </select>
            </div>

            <div className="input-group mb-3">
                < input type="text" id="inputFilter" onChange={(e) => this.handleFilterText(e)} value={this.state.filterText} className="form-control" placeholder="Enter Your Search Term Here" aria-label="Enter Your Search Term Here" aria-describedby="basic-addon2" />
            </div>

            <table className="table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Role</th>
                        <th>Joining Date</th>
                        <th>Status</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    {filtered.map(user => (
                        <tr key={user["userId"].toString()}>
                            <td>{user["userId"].toString()}</td>
                            <td>{(user["fullName"] == null) ? 'na' : user["fullName"].toString()}</td>
                            <td>{(user["emailId"] == null) ? 'na' : user["emailId"].toString()}</td>
                            <td>{(user["role"] == null) ? 'na' : user["role"].toString()}</td>
                            <td>{(user["joiningDate"] == null) ? 'na' : user["joiningDate"].toString()}</td>
                            <td>{(user["status"] == null) ? 'na' : user["status"].toString()}</td>

                            {/* <td><Link to={"/" + userx + "/users/" + user["userId"].toString()} className="btn btn-primary btn-sm">Details</Link></td> */}
                            <td><Link to={"/"+userx + "/" + uid + "/users/"  + user["userId"].toString()} className="btn btn-primary btn-sm">Details</Link></td>
                        </tr>
                    ))}
                </tbody>
            </table>

            <div className="row">
                <div className="col-sm">
                </div>

                <div className="col-sm">
                </div>

                <div className="col-sm">
                </div>

                <div className="col-sm">
                </div>

                <div className="col-sm">
                <Link to={"/"+userx + "/" + uid +  "/users/add" } className="btn btn-secondary">Add User</Link>
                </div>
            </div>

        </React.Fragment>;

        }
        
    }
}

export default UserManagement;