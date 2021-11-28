import React, { Component } from 'react';
class AnimalProfile extends React.Component {
    state = {
        
        
        imageUrl: 'https://picsum.photos/200',
        
    };
    render() { 
        return <React.Fragment>
            <div class="container">
                <div class="row">
                    <div class="col-sm">
                    <img src={this.state.imageUrl} alt=""/>

                    </div>
                    <div class="col-sm">
                    <div class="jumbotron jumbotron-fluid">
                    <div class="container">
                        <h5 class="display-4">Basic Details</h5>
                        <p class="lead">Animal ID: </p>
                        <p class="lead">Name: </p>
                        <p class="lead">Breed: </p>
                        <p class="lead">Age: </p>
                        <p class="lead">Sex: </p>
                        <p class="lead">Status: </p>
                    </div>
                    </div>
                    </div>
                    
                </div>
                <div class="row">
                    <div class="col-sm">
                        <div class="jumbotron jumbotron-fluid">
                            <div class="container">
                                <h5 class="display-4">Owner Details</h5>
                                <p class="lead">Owner Name: </p>
                                <p class="lead">Owner Contact: </p>
                                <p class="lead">Owner Email: </p>
                                <p class="lead">Owner Address: </p>
                                
                            </div>
                        </div>

                    </div>
                    <div class="col-sm">
                        <div class="jumbotron jumbotron-fluid">
                        <div class="container">
                            <h5 class="display-4">Identification Details</h5>
                            <p class="lead">RFID Number: </p>
                            <p class="lead">Microchip: </p>
                            <p class="lead">Tattoo: </p>
                            <p class="lead">Coat Color: </p>
                            
                        </div>
                        </div>
                    </div>
                    
                </div>
                <div class="row">
                    <div class="jumbotron jumbotron-fluid">
                        <div class="container">
                            <h5 class="display-4">Weight Graph</h5>
                            
                            
                        </div>
                    </div>

                </div>  
                <div class="row">
                    <div class="jumbotron jumbotron-fluid">
                        <div class="container">
                            <h5 class="display-4">Other Details</h5>
                            <p class="lead">Continuous Medication: </p>
                            
                            
                        </div>
                    </div>

                </div>  

                <div class="row">
                    <div class="jumbotron jumbotron-fluid">
                        <div class="container">
                            <h5 class="display-4">Photos</h5>
                            
                            
                        </div>
                    </div>

                </div> 
                <div class="row">
                <div class="btn-group" role="group" aria-label="Basic example">
                <button type="button" class="btn btn-secondary">Treat List</button>
                <button type="button" class="btn btn-secondary">Issue List</button>
                <button type="button" class="btn btn-secondary">Comment List</button>
                </div>

                </div>   
            </div>
            


            </React.Fragment>;
    }
}
 
export default AnimalProfile;