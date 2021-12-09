import React, { Component } from 'react';
import axios from 'axios';
import NavBar from './navbar';
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link
} from "react-router-dom";
class IsAnimalOwner extends React.Component {
    state = {
        
        
        
        ownerImageUrl: process.env.PUBLIC_URL + '/withowner.png',
        noOwnerImageUrl: process.env.PUBLIC_URL + '/newowner.png',
        
        
        
        
    };
    styles = {
        width: 18+'rem'
        

    };
    
    render() { 
        const user = this.props.match.params.user;
        const uid = this.props.match.params.uid;
        return <React.Fragment>
            <NavBar user = {user} uid = {uid}/>
                <div class="row">
                <div class="card" style={this.styles}>
                <a href={"/"+user+"/" + uid +"/animals/addoanimal"}>
                    <img class="card-img-top" src={this.state.ownerImageUrl} alt="Card image cap"/>
                    <div class="card-body">
                        <h5 class="card-title">Existing Owner</h5>
                        
                    </div>
                    </a>
                </div>

                <div class="card" style={this.styles}>
                <a href={"/"+user+"/" + uid +"/animals/addnanimal"}>
                    <img class="card-img-top" src={this.state.noOwnerImageUrl} alt="Card image cap"/>
                    <div class="card-body">
                        <h5 class="card-title">New Owner</h5>
                        
                    </div>
                    </a>
                </div>
                </div>
        </React.Fragment>;
    }
}
 
export default IsAnimalOwner;