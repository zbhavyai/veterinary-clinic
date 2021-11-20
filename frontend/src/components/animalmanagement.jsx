import React, { Component } from 'react';
import style from './style.css'

import {getAnimals} from '../services/fakeAnimalsService'
class AnimalManagement extends React.Component {
    state = {
        animals: getAnimals(),
        pageSize: 4
    }
    render() { 
        return <React.Fragment>
        <button> Register</button>
        <button> Sign In</button>

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
                    <td>{(animal["theOwner"]==null) ? 'na' : animal["theOwner"].emailId.toString()}</td>
                    <td>{(animal["status"]==null) ? 'na' : animal["status"].toString()}</td>
                    
                    </tr>

                ))}
            

                
                
            </tbody>
        </table>
        

      </React.Fragment>
        ;
    }
}
 
export default AnimalManagement;