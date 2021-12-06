import React, { Component } from 'react';
import NavBar from './navbar';

class MainMenu extends React.Component {
    state = {
        
        
        
        userImageUrl: process.env.PUBLIC_URL + '/users.png',
        animalImageUrl: process.env.PUBLIC_URL + '/animals.png',
        submitImageUrl: process.env.PUBLIC_URL + '/requestsubmission.png',
        requestImageUrl: process.env.PUBLIC_URL + '/requests.png',

        
        
        
    };
    styles = {
        width: 18+'rem'
        

    };
    render() { 
        const user = this.props.match.params.user;
        const uid = this.props.match.params.uid;

        let animmanagementhyperlink = "/"+ user + "/" + uid + "/animals";
        let usermanagementhyperlink = "/"+ user + "/" + uid + "/users";
        let requestmanagementhyperlink = "/"+ user + "/" + uid + "/requestmanagment";
        let requestsubmissionhyperlink = "/"+ user + "/" + uid + "/requestsubmission";
        if(user == "at" ){
            return <React.Fragment>
                <NavBar user = {user} uid = {uid}/>
                
                <div class="card" style={this.styles}>
                <a href={animmanagementhyperlink}>
                    <img class="card-img-top" src={this.state.animalImageUrl} alt="Card image cap"/>
                    <div class="card-body">
                        <h5 class="card-title">Animal Management</h5>
                        
                    </div>
                    </a>
                </div>
                
            </React.Fragment>;

        }
        if(user == "s"){
            return <React.Fragment>
                <NavBar user = {user} uid = {uid}/>
                
                <div class="card" style={this.styles}>
                <a href={animmanagementhyperlink}>
                    <img class="card-img-top" src={this.state.animalImageUrl} alt="Card image cap"/>
                    <div class="card-body">
                        <h5 class="card-title">Animal Management</h5>
                        
                    </div>
                    </a>
                </div>
                
            </React.Fragment>;

        }

        if(user == "i"){
            return <React.Fragment>
                <NavBar user = {user} uid = {uid}/>
                <div class="row">
                <div class="card" style={this.styles}>
                <a href={usermanagementhyperlink}>
                    <img class="card-img-top" src={this.state.userImageUrl} alt="Card image cap"/>
                    <div class="card-body">
                        <h5 class="card-title">Student Management</h5>
                        
                    </div>
                    </a>
                </div>

                <div class="card" style={this.styles}>
                <a href={animmanagementhyperlink}>
                    <img class="card-img-top" src={this.state.animalImageUrl} alt="Card image cap"/>
                    <div class="card-body">
                        <h5 class="card-title">Animal Management</h5>
                        
                    </div>
                    </a>
                </div>
                <div class="card" style={this.styles}>
                <a href={requestsubmissionhyperlink}>
                    <img class="card-img-top" src={this.state.submitImageUrl} alt="Card image cap"/>
                    <div class="card-body">
                        <h5 class="card-title">Request Submission</h5>
                        
                    </div>
                    </a>
                </div>
                
                </div>
            </React.Fragment>;

        }


        if(user == "a"){
            return <React.Fragment>
                <NavBar user = {user} uid = {uid}/>
                <div class="row">
                    
                <div class="card" style={this.styles}>
                    <a href={usermanagementhyperlink}>
                    <img class="card-img-top" src={this.state.userImageUrl} alt="Card image cap"/>
                    <div class="card-body">
                        <h5 class="card-title">User Management</h5>
                        
                    </div>
                    </a>
                </div>

                <div class="card" style={this.styles}>
                <a href={animmanagementhyperlink}>
                    <img class="card-img-top" src={this.state.animalImageUrl} alt="Card image cap"/>
                    <div class="card-body">
                        <h5 class="card-title">Animal Management</h5>
                        
                    </div>
                    </a>
                </div>
                
                <div class="card" style={this.styles}>
                <a href={requestmanagementhyperlink}>
                    <img class="card-img-top" src={this.state.requestImageUrl} alt="Card image cap"/>
                    <div class="card-body">
                        <h5 class="card-title">Request Management</h5>
                        
                    </div>
                    </a>
                </div>
                </div>
            </React.Fragment>;

        }

        if(user == "t"){
            return <React.Fragment>
                <NavBar user = {user} uid = {uid}/>
                <div class="row">
                

                <div class="card" style={this.styles}>
                <a href={animmanagementhyperlink}>
                    <img class="card-img-top" src={this.state.animalImageUrl} alt="Card image cap"/>
                    <div class="card-body">
                        <h5 class="card-title">Animal Management</h5>
                        
                    </div>
                    </a>
                </div>
                
                <div class="card" style={this.styles}>
                <a href={requestmanagementhyperlink}>
                    <img class="card-img-top" src={this.state.requestImageUrl} alt="Card image cap"/>
                    <div class="card-body">
                        <h5 class="card-title">Request Management</h5>
                        
                    </div>
                    </a>
                </div>
                </div>
            </React.Fragment>;

        }
        
    }
}
 
export default MainMenu;