import React from 'react';
import axios from "axios";

import NavBar from './navbar';

import {
    

    Link
} from "react-router-dom";
class EditUser extends React.Component {
    state = {
        user: {},
        
        filterOption: 0,
        
        firstName: "",
        middleName: "",
        lastName: "",
        role: "ADMIN",
        emailId: "",
        password: "",
        status: "INACTIVE"
        
        
    };

    async componentDidMount() {
        const id = this.props.match.params.id;

        var userUrl = "http://localhost:8080/api/v1/users/" + id;
        const { data: user } = await axios.get(userUrl, { headers: { 'Access-Control-Allow-Origin': true, }, });
        this.setState({ user });

        let trigger = 0;
        if(this.state.user["role"] == "ADMIN"){
            trigger = 0;
        } else if(this.state.user["role"] == "ATTENDANT"){
            trigger = 1;
        } else if(this.state.user["role"] == "TECHNICIAN"){
            trigger = 2;
        } else if(this.state.user["role"] == "TEACHER"){
            trigger = 3;
        } else if(this.state.user["role"] == "STUDENT"){
            trigger = 4;
        }

        this.setState({
            filterOption: trigger,
        
            firstName: this.state.user["firstName"],
            middleName: this.state.user["middleName"],
            lastName: this.state.user["lastName"],
            role: this.state.user["role"],
            emailId: this.state.user["emailId"],
            password: this.state.user["passwordHash"],
            status: this.state.user["status"]

        });

        

        //const promise = axios.get('https://jsonplaceholder.typicode.com/posts')
    }

    handleFilter = (e) => {
        let pos = "";
        this.setState({ 
            filterOption: e.target.value,
            role: pos, 
        });

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
            "userId": this.state.user["userId"],
            "firstName": this.state.firstName,
            "middleName": this.state.middleName,
            "lastName": this.state.lastName,
            "role": this.state.role,
            "emailId": this.state.emailId,
            "passwordHash": this.state.password,
            
            "status": this.state.status
        }
        
        
       
        
        const link = "http://localhost:8080/api/v1/users/" + this.state.user["userId"];
        axios.put(link, message,{headers:{}}).then(res => {
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
                      < input type="text" id="inputFilter"  className="form-control" placeholder={this.state.firstName} value={this.state.firstName} onChange={(e) =>this.handleFnChange(e)} aria-label="First Name" aria-describedby="basic-addon2" />
                    
                      </div>
                      <div className="col-sm">
                      < input type="text" id="inputFilter" className="form-control" placeholder={this.state.middleName} value={this.state.middleName} onChange={(e) =>this.handleMnChange(e)} aria-label="Middle Name" aria-describedby="basic-addon2" />
                    
                      </div>
                      <div className="col-sm">
                      < input type="text" id="inputFilter" className="form-control" placeholder={this.state.lastName} value={this.state.lastName} onChange={(e) =>this.handleLnChange(e)} aria-label="Last Name" aria-describedby="basic-addon2" />
                    
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
                    < input type="text" id="inputFilter" className="form-control" placeholder={this.state.emailId} value={this.state.emailId} onChange={(e) =>this.handleEmChange(e)} aria-label="Email Address" aria-describedby="basic-addon2" />
                    </div>
                    <div className="row">
                    </div>

                    <button onClick={(e) => this.handleRegister(e)} className="btn btn-primary">Save</button><Link to={"/"+userx+"/" + uid +"/users/" + this.state.user["userId"] } className="btn btn-primary">Cancel</Link>
                    
                </div>
            </div>
            </div>
        </React.Fragment>;
    }
}
 
export default EditUser;