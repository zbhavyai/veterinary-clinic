import React from 'react';

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
            <div className="card" style={this.styles}>
                <img className="card-img-top" src={this.state.imageUrl} alt="Card  cap"/>
                <div className="card-body">
                    <h5 className="card-title">Welcome to VetApp</h5>
                    <p className="card-text">Please Sign in or Register from the top menu.</p>
                    
                </div>
                </div>
            


        </React.Fragment>;
    }
}
 
export default HomePage;