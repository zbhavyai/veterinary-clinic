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
        if(user == "at" ){
            return <React.Fragment>
                <NavBar user = {user}/>
                
                <div class="card" style={this.styles}>
                <a href="/at/animals">
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
                <NavBar user = {user}/>
                
                <div class="card" style={this.styles}>
                <a href="/s/animals">
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
                <NavBar user = {user}/>
                <div class="row">
                <div class="card" style={this.styles}>
                <a href="/i/users">
                    <img class="card-img-top" src={this.state.userImageUrl} alt="Card image cap"/>
                    <div class="card-body">
                        <h5 class="card-title">User Management</h5>
                        
                    </div>
                    </a>
                </div>

                <div class="card" style={this.styles}>
                <a href="/i/animals">
                    <img class="card-img-top" src={this.state.animalImageUrl} alt="Card image cap"/>
                    <div class="card-body">
                        <h5 class="card-title">Animal Management</h5>
                        
                    </div>
                    </a>
                </div>
                <div class="card" style={this.styles}>
                <a href="/i/requestsubmission">
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
                <NavBar user = {user}/>
                <div class="row">
                    
                <div class="card" style={this.styles}>
                    <a href="/a/users">
                    <img class="card-img-top" src={this.state.userImageUrl} alt="Card image cap"/>
                    <div class="card-body">
                        <h5 class="card-title">User Management</h5>
                        
                    </div>
                    </a>
                </div>

                <div class="card" style={this.styles}>
                <a href="/a/animals">
                    <img class="card-img-top" src={this.state.animalImageUrl} alt="Card image cap"/>
                    <div class="card-body">
                        <h5 class="card-title">Animal Management</h5>
                        
                    </div>
                    </a>
                </div>
                
                <div class="card" style={this.styles}>
                <a href="/a/requestmanagment">
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
                <NavBar user = {user}/>
                <div class="row">
                <div class="card" style={this.styles}>
                <a href="/t/users">
                    <img class="card-img-top" src={this.state.userImageUrl} alt="Card image cap"/>
                    <div class="card-body">
                        <h5 class="card-title">User Management</h5>
                        
                    </div>
                    </a>
                </div>

                <div class="card" style={this.styles}>
                <a href="/t/animals">
                    <img class="card-img-top" src={this.state.animalImageUrl} alt="Card image cap"/>
                    <div class="card-body">
                        <h5 class="card-title">Animal Management</h5>
                        
                    </div>
                    </a>
                </div>
                
                <div class="card" style={this.styles}>
                <a href="/t/requestmanagment">
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