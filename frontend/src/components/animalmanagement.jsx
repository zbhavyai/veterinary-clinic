import React, { Component } from 'react';
import {getAnimals} from '../services/fakeAnimalsService'



class AnimalManagement extends React.Component {
    state = {
        animals: getAnimals(),
        pageSize: 4
    }
    render() { 
        return <React.Fragment>
            
           
           

                <div class="input-group mb-3">
                    <label class="input-group-text" for="inputGroupSelect01">Search For</label>
                    <select class="form-select" id="inputGroupSelect01">
                        <option selected>Choose...</option>
                        <option value="1">ID</option>
                        <option value="2">Name</option>
                        <option value="3">Breed</option>
                        <option value="4">Owner</option>
                        <option value="5">Owner</option>
                    </select>
                </div>

                <div class="input-group mb-3">
                    < input type="text" class="form-control" placeholder="Enter Your Search Term Here" aria-label="Enter Your Search Term Here" aria-describedby="basic-addon2" />
                    
                </div>
        
        <table className="table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Breed</th>
                    <th>Owner</th>
                    <th>Status</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                {this.state.animals.map(animal =>(
                    <tr key = {animal["animalId"].toString()}>
                    <td>{animal["animalId"].toString()}</td>
                    <td>{(animal["name"]==null) ? 'na' : animal["name"].toString()}</td>
                    <td>{(animal["breed"]==null) ? 'na' : animal["breed"].toString()}</td>
                    <td>{(animal["theOwner"]==null) ? 'na' : animal["theOwner"]["emailId"].toString()}</td>
                    <td>{(animal["status"]==null) ? 'na' : animal["status"].toString()}</td>
                    <td><button  className="btn btn-danger btn-sm">Details</button></td>
                    
                    </tr>

                ))}
            

                
                
            </tbody>
        </table>
        

      </React.Fragment>
        ;
    }
}
 
export default AnimalManagement;