import React, { Component } from 'react';
import axios from "axios";
import NavBar from "./navbar";
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link
} from "react-router-dom";

class UserDetails extends React.Component {
    state = {
        user: {}
    };

    async componentDidMount() {
        const id = this.props.match.params.id;

        var userUrl = "http://localhost:8080/api/v1/users/" + id;
        const { data: user } = await axios.get(userUrl, { headers: { 'Access-Control-Allow-Origin': true, }, });
        this.setState({ user });

        //const promise = axios.get('https://jsonplaceholder.typicode.com/posts')
    }

    //WARNING! To be deprecated in React v17. Use componentDidMount instead.
    // componentWillMount() {
    //     const id = this.props.match.params.id;
    //     // console.log(id);

    //     this.setState({
    //         user: getUserbyId(id),
    //     });
    // }


    handleStatus =(e, statusx)=>{
        const id = this.props.match.params.id;

        const message = {
            "userId": this.state.user["userId"],
            "firstName": this.state.user["firstName"],
            "middleName": this.state.user["middleName"],
            "lastName": this.state.user["lastName"],
            "role": this.state.user["role"],
            "emailId": this.state.user["emailId"],
            "password": this.state.user["password"],
            "status": statusx
        };
       
        
        const link = "http://localhost:8080/api/v1/users/" + id;
        axios.put(link, message,{headers:{}});
        window.location.reload(false);
        





    };

    render() {
        const userx = this.props.match.params.user;
        const uid = this.props.match.params.uid;
        // let label = "";

        // if (this.state.user["role"] === 0) {
        //     label = "Student";
        // }

        // else if (this.state.user["role"] === 1) {
        //     label = "Technician";
        // }

        // else if (this.state.user["role"] === 2) {
        //     label = "Teacher";
        // }

        // else if (this.state.user["role"] === 3) {
        //     label = "Admin";
        // }

        if(userx == "i"){
            return <React.Fragment>
            <NavBar user = {userx} uid = {uid}/>

            <div class="card" style={this.styles}>
                <div class="card-body">
                    <h1 class="card-title">User Details</h1>
                    <div class="row">
                        <div class="col-sm">
                        <p class="lead">First Name: {this.state.user["firstName"]}</p>
                             </div>

                        <div class="col-sm">
                        <p class="lead">Middle Name: {this.state.user["middleName"]}</p>
                        </div>

                        <div class="col-sm">
                        <p class="lead">Last Name: {this.state.user["lastName"]}</p>
                            </div>
                    </div>

                    <div class="row">
                    <p class="lead">Your Unique ID: {this.state.user["userId"]}</p>
                        </div>

                    <div class="row">
                        {/* <select className="form-select" id="selectFilter" defaultValue={{ label: this.state.user["role"].toString(), value: this.state.user["role"].toString() }}>
                            <option value="0">Student</option>
                            <option value="1">Technician</option>
                            <option value="2">Teacher</option>
                            <option value="3">Admin</option>
                        </select> */}
                        <p class="lead">Role: {this.state.user["role"]}</p>
                        </div>

                    <div class="row">
                    <p class="lead">Email: {this.state.user["emailId"]}</p>
                        </div>

                    <div class="row">
                    <p class="lead">Status: {this.state.user["status"]}</p>
                        </div>

                        <button onClick={(e) => this.handleStatus(e, "INACTIVE")} className="btn btn-primary">Block</button><button onClick={(e) => this.handleStatus(e, "ACTIVE")} className="btn btn-primary ">Unblock</button>
                </div>
            </div>

        </React.Fragment>;


        } else{
            return <React.Fragment>
            <NavBar user = {userx} uid = {uid}/>

            <div class="card" style={this.styles}>
                <div class="card-body">
                    <h1 class="card-title">User Details</h1>
                    <div class="row">
                        <div class="col-sm">
                        <p class="lead">First Name: {this.state.user["firstName"]}</p>
                             </div>

                        <div class="col-sm">
                        <p class="lead">Middle Name: {this.state.user["middleName"]}</p>
                        </div>

                        <div class="col-sm">
                        <p class="lead">Last Name: {this.state.user["lastName"]}</p>
                            </div>
                    </div>

                    <div class="row">
                    <p class="lead">Your Unique ID: {this.state.user["userId"]}</p>
                        </div>

                    <div class="row">
                        {/* <select className="form-select" id="selectFilter" defaultValue={{ label: this.state.user["role"].toString(), value: this.state.user["role"].toString() }}>
                            <option value="0">Student</option>
                            <option value="1">Technician</option>
                            <option value="2">Teacher</option>
                            <option value="3">Admin</option>
                        </select> */}
                        <p class="lead">Role: {this.state.user["role"]}</p>
                        </div>

                    <div class="row">
                    <p class="lead">Email: {this.state.user["emailId"]}</p>
                        </div>

                    <div class="row">
                    <p class="lead">Status: {this.state.user["status"]}</p>
                        </div>

                    <a href="#" class="btn btn-primary">Edit</a>
                </div>
            </div>

        </React.Fragment>;

        }

        
    }
}

export default UserDetails;
