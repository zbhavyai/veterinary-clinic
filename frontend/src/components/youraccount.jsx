import React from 'react';
import axios from "axios";
import NavBar from "./navbar";

class YourAccount extends React.Component {
    state = {
        user: {}
    };

    async componentDidMount() {
        const id = this.props.match.params.uid;

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

        
            return <React.Fragment>
            <NavBar user = {userx} uid = {uid}/>

            <div className="card" style={this.styles}>
                <div className="card-body">
                    <h1 className="card-title">User Details</h1>
                    <div className="row">
                        <div className="col-sm">
                        <p className="lead">First Name: {this.state.user["firstName"]}</p>
                             </div>

                        <div className="col-sm">
                        <p className="lead">Middle Name: {this.state.user["middleName"]}</p>
                        </div>

                        <div className="col-sm">
                        <p className="lead">Last Name: {this.state.user["lastName"]}</p>
                            </div>
                    </div>

                    <div className="row">
                    <p className="lead">Your Unique ID: {this.state.user["userId"]}</p>
                        </div>

                    <div className="row">
                        {/* <select className="form-select" id="selectFilter" defaultValue={{ label: this.state.user["role"].toString(), value: this.state.user["role"].toString() }}>
                            <option value="0">Student</option>
                            <option value="1">Technician</option>
                            <option value="2">Teacher</option>
                            <option value="3">Admin</option>
                        </select> */}
                        <p className="lead">Role: {this.state.user["role"]}</p>
                        </div>

                    <div className="row">
                    <p className="lead">Email: {this.state.user["emailId"]}</p>
                        </div>

                    <div className="row">
                    <p className="lead">Status: {this.state.user["status"]}</p>
                        </div>

                        
                </div>
            </div>

        </React.Fragment>;


        

        
    }
}
 
export default YourAccount;