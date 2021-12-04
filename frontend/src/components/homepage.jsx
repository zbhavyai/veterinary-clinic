import React, { Component } from 'react';
import NavBar from './navbar';
import NavBarStart from './navbarstart';

class HomePage extends React.Component {
    state = {
        
        
        
        imageUrl: process.env.PUBLIC_URL +'/logo.png',

        
        
        
    };

    styles = {
        width: 18+'rem'
        

    };
    render() { 
        
        return <React.Fragment>
            <NavBarStart/>
            <div class="card" style={this.styles}>
                <img class="card-img-top" src={this.state.imageUrl} alt="Card image cap"/>
                <div class="card-body">
                    <h5 class="card-title">Welcome to VetApp</h5>
                    <p class="card-text">Please Sign in or Register from the top menu.</p>
                    
                </div>
                </div>
            


        </React.Fragment>;
    }
}
 
export default HomePage;