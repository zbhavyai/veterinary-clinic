import React, { Component } from 'react';
import NavBar from "./navbar";
class YourAccount extends React.Component {
    render() { 
        const user = this.props.match.params.user;
        
        const uid = this.props.match.params.uid;
        return <React.Fragment>
            <NavBar user = {user} uid = {uid}/>
            <div class="card" style={this.styles}>
                
                <div class="card-body">
                    <h5 class="card-title">Your Account Details</h5>
                    <div class="row">
                      <div class="col-sm">
                      < input type="text" id="inputFilter"  className="form-control" placeholder="First Name" aria-label="First Name" aria-describedby="basic-addon2" />
                    
                      </div>
                      <div class="col-sm">
                      < input type="text" id="inputFilter" className="form-control" placeholder="Middle Name" aria-label="Middle Name" aria-describedby="basic-addon2" />
                    
                      </div>
                      <div class="col-sm">
                      < input type="text" id="inputFilter" className="form-control" placeholder="Last Name" aria-label="Last Name" aria-describedby="basic-addon2" />
                    
                      </div>
                      
                      
                    </div>
                    <div class="row">
                    < input type="text" id="inputFilter" className="form-control" placeholder="Your Unique ID" aria-label="Your Unique ID" aria-describedby="basic-addon2" />
                    </div>

                    <div class="row">
                    <select   className="form-select" id="selectFilter" >
                        <option value = "0">Student</option>
                        <option value="1">Technician</option>
                        <option value="2">Teacher</option>
                        <option value="3">Admin</option>
                        
                    </select>

                    </div>
                    <div class="row">
                    < input type="text" id="inputFilter" className="form-control" placeholder="Email Address" aria-label="Email Address" aria-describedby="basic-addon2" />
                    </div>
                    <div class="row">
                    < input type="text" id="inputFilter" className="form-control" placeholder="Old Password" aria-label="Old Password" aria-describedby="basic-addon2" />
                    </div>

                    <div class="row">
                    < input type="text" id="inputFilter" className="form-control" placeholder="New Password" aria-label="New Password" aria-describedby="basic-addon2" />
                    </div>

                    <a href="#" class="btn btn-primary">Save</a><a href="#" class="btn btn-primary">Cancel</a>
                </div>
            </div>

        </React.Fragment>;
    }
}
 
export default YourAccount;