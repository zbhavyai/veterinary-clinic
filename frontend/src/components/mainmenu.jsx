import React, { Component } from 'react';
import NavBar from './navbar';
class MainMenu extends React.Component {
    styles = {
        width: 18+'rem'
        

    };
    render() { 
        
        return <React.Fragment>
            <NavBar/>
            <div class="row">
            <div class="card" style={this.styles}>
                <img class="card-img-top" src="https://picsum.photos/200" alt="Card image cap"/>
                <div class="card-body">
                    <h5 class="card-title">User Management</h5>
                    <a href="/users" class="btn btn-primary">Go</a>
                </div>
            </div>

            <div class="card" style={this.styles}>
                <img class="card-img-top" src="https://picsum.photos/200" alt="Card image cap"/>
                <div class="card-body">
                    <h5 class="card-title">Animal Management</h5>
                    <a href="/animals" class="btn btn-primary">Go</a>
                </div>
            </div>
            </div>




        </React.Fragment>;
    }
}
 
export default MainMenu;