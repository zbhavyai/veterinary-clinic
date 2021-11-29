import React, { Component } from 'react';
import {getAnimals} from '../services/fakeAnimalsService'



class AnimalManagement extends React.Component {
    state = {
        animals: getAnimals(),
        filterOption: 0,
        filterText: "",
        pageSize: 4
    };

    handleFilter =(e)=>{
        this.setState({filterOption:e.target.value});
        //this.setState({filterOption: 1});
        console.log("ID clicked", this.state.filterOption);

    };

    handleFilterText =(e)=>{
        this.setState({filterText:e.target.value});
        //this.setState({filterOption: 1});
        console.log("tedt changed", this.state.filterText);

    };




    render() { 
        let filtered = this.state.animals;
        if (this.state.filterOption == 1) {
            filtered = 1?this.state.animals.filter(m=>m["animalId"].toString().toLowerCase().includes(this.state.filterText.toLowerCase()) ):this.state.animals;
          } else if (this.state.filterOption == 2) {
            filtered = 1?this.state.animals.filter(m=>m["name"].toString().toLowerCase().includes(this.state.filterText.toLowerCase()) ):this.state.animals;
          } else if (this.state.filterOption == 3) {
            filtered = 1?this.state.animals.filter(m=>m["breed"].toString().toLowerCase().includes(this.state.filterText.toLowerCase()) ):this.state.animals;
          } else if (this.state.filterOption == 4) {
            filtered = 1?this.state.animals.filter(m=>m["theOwner"]["emailId"].toString().toLowerCase().includes(this.state.filterText.toLowerCase())):this.state.animals;
          } else if (this.state.filterOption == 5) {
            filtered = 1?this.state.animals.filter(m=>m["status"].toString().toLowerCase().includes(this.state.filterText.toLowerCase()) ):this.state.animals;
          } else{
            filtered = this.state.animals;
          }
        
        
        
        return <React.Fragment>
                <div class="container">
                  
                </div>
            
            
           
                
                
                <div className="input-group mb-3">
                    
                    <label className="input-group-text" htmlFor ="inputGroupSelect01">Search For</label>
                    <select   onChange = {(e) => this.handleFilter(e)} value = {this.state.filterOption}  className="form-select" id="selectFilter" >
                        <option value = "0">No Filter</option>
                        <option value="1">ID</option>
                        <option value="2">Name</option>
                        <option value="3">Breed</option>
                        <option value="4">Owner's Email</option>
                        <option value="5">Status</option>
                    </select>
                </div>

                <div className="input-group mb-3">
                    < input type="text" id="inputFilter" onChange = {(e) => this.handleFilterText(e)} value = {this.state.filterText} className="form-control" placeholder="Enter Your Search Term Here" aria-label="Enter Your Search Term Here" aria-describedby="basic-addon2" />
                    
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
                {filtered.map(animal =>(
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
        <div class="row">
                      <div class="col-sm">
                        
                      </div>
                      <div class="col-sm">
                        
                      </div>
                      <div class="col-sm">
                        
                      </div>
                      <div class="col-sm">
                        
                      </div>
                      <div class="col-sm">
                        <button type="button" class="btn btn-secondary">Add Animal</button>
                      </div>
                      
        </div>
        

      </React.Fragment>
        ;
    }
}
 
export default AnimalManagement;