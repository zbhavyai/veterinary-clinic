import React from 'react';
import axios from "axios";

import NavBar from './navbar';

class AddUser
 extends React.Component {
    state = {

        filterOption: 0,

        firstName: "",
        middleName: "",
        lastName: "",
        role: "ADMIN",
        emailId: "",
        password: "",
        status: "INACTIVE"


    };

    handleFilter = (e) => {
        let pos = "";
        console.log("Choice: " + e.target.value);

        if(e.target.value==0){
            pos = "ADMIN";

        }else if(e.target.value==1){
            pos = "ATTENDANT";

        }else if(e.target.value==2){
            pos = "TECHNICIAN";

        }else if(e.target.value==3){
            pos = "TEACHER";

        }else if(e.target.value==4){
            pos = "STUDENT";

        }

        this.setState({
            filterOption: e.target.value,
            role: pos,
        });
        //this.setState({filterOption: 1});
        console.log("Role Selected", this.state.role);

    };

    handleFnChange(event) {
        this.setState({firstName: event.target.value})

      }

      handleMnChange(event) {
        this.setState({middleName: event.target.value})

      }

      handleLnChange(event) {
        this.setState({lastName: event.target.value})

      }

      handlePsChange(event) {
        this.setState({password: event.target.value})

      }

      handleEmChange(event) {
        this.setState({emailId: event.target.value})

      }


      handleRegister =(e)=>{

        const userx = this.props.match.params.user;
        const uid = this.props.match.params.uid;




        let message = {
            "userId": 3,
            "joiningDate": "2017-12-01",
            "activationDate": "2017-12-02",
            "terminationDate": null,
            "firstName": this.state.firstName,
            "middleName": this.state.middleName,
            "lastName": this.state.lastName,
            "role": this.state.role,
            "emailId": this.state.emailId,
            "password": this.state.password,
            "passwordSalt": null,
            "status": this.state.status
        }

        console.log(message)


        const link = "http://localhost:8080/api/v1/users/register";
        axios.post(link, message,{headers:{}}).then(res => {
            console.log(res);
            console.log(res.data);
          });
        //window.location.reload(false);


        setTimeout(() => {
            this.props.history.push("/"+ userx+"/" + uid +'/users/');
         }, 500);





    };


    render() {
        const userx = this.props.match.params.user;
        const uid = this.props.match.params.uid;
        return <React.Fragment>
            <NavBar user = {userx} uid = {uid}/>
            <div className="container">
            <div className="card" style={this.styles}>

                <div className="card-body">
                    <h5 className="card-title">Register for an Account Now</h5>
                    <div className="row">
                      <div className="col-sm">
                      < input type="text" id="inputFilter"  className="form-control" placeholder="First Name" value={this.state.firstName} onChange={(e) =>this.handleFnChange(e)} aria-label="First Name" aria-describedby="basic-addon2" />

                      </div>
                      <div className="col-sm">
                      < input type="text" id="inputFilter" className="form-control" placeholder="Middle Name" value={this.state.middleName} onChange={(e) =>this.handleMnChange(e)} aria-label="Middle Name" aria-describedby="basic-addon2" />

                      </div>
                      <div className="col-sm">
                      < input type="text" id="inputFilter" className="form-control" placeholder="Last Name" value={this.state.lastName} onChange={(e) =>this.handleLnChange(e)} aria-label="Last Name" aria-describedby="basic-addon2" />

                      </div>


                    </div>

                    <div className="row">
                    <select   onChange={(e) => this.handleFilter(e)} className="form-select" id="selectFilter" value={this.state.filterOption}>
                        <option value = "0">ADMIN</option>
                        <option value="1">ATTENDANT</option>
                        <option value="2">TECHNICIAN</option>
                        <option value="3">TEACHER</option>
                        <option value="4">STUDENT</option>

                    </select>

                    </div>
                    <div className="row">
                    < input type="text" id="inputFilter" className="form-control" placeholder="Email Address" value={this.state.emailId} onChange={(e) =>this.handleEmChange(e)} aria-label="Email Address" aria-describedby="basic-addon2" />
                    </div>
                    <div className="row">
                    < input type="text" id="inputFilter" className="form-control" placeholder="Create Password" value={this.state.password} onChange={(e) =>this.handlePsChange(e)} aria-label="Create Password" aria-describedby="basic-addon2" />
                    </div>

                    <button onClick={(e) => this.handleRegister(e)} className="btn btn-primary">Register</button>
                    <p>Note: Upon sign up, you will need to wait for the Admin to activate your account before you can login</p>
                </div>
            </div>
            </div>
        </React.Fragment>;
    }
}

export default AddUser
;