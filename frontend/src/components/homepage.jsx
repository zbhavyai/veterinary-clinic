import React, { Component } from 'react';
import NavBar from './navbar';
import NavBarStart from './navbarstart';
class HomePage extends React.Component {
    state = {
        
        
        
        imageUrl: 'https://schulich.ucalgary.ca/sites/default/files/styles/ucws_profiles_profile_picture/public/2018-12/Moshirpour%2C%20Mohammad.jpg?h=442ae63a&itok=k6Ifu8et',

        
        
        
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
                    <p class="card-text">This App is Awesome.  -Someone</p>
                    
                </div>
                </div>
            


        </React.Fragment>;
    }
}
 
export default HomePage;