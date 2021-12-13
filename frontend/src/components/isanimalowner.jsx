import React from 'react';

import NavBar from './navbar';

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
                <div className="row">
                <div className="card" style={this.styles}>
                <a href={"/"+user+"/" + uid +"/animals/addoanimal"}>
                    <img className="card-img-top" src={this.state.ownerImageUrl} alt="Card  cap"/>
                    <div className="card-body">
                        <h5 className="card-title">Existing Owner</h5>
                        
                    </div>
                    </a>
                </div>

                <div className="card" style={this.styles}>
                <a href={"/"+user+"/" + uid +"/animals/addnanimal"}>
                    <img className="card-img-top" src={this.state.noOwnerImageUrl} alt="Card a cap"/>
                    <div className="card-body">
                        <h5 className="card-title">New Owner</h5>
                        
                    </div>
                    </a>
                </div>
                </div>
        </React.Fragment>;
    }
}
 
export default IsAnimalOwner;