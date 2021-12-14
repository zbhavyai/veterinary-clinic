import React from 'react';
import axios from 'axios';
import NavBar from './navbar';
import {
    
    
    Link
} from "react-router-dom";


class AnimalManagement extends React.Component {
    state = {
        
        animals: [],
        // animals: getAnimals(),
        filterOption: 0,
        filterText: "",
        pageSize: 10
    };

    handleInputText = (e) => {
        this.setState({ inputText: e.target.value });
        //this.setState({filterOption: 1});
        console.log("tedt changed", this.state.filterText);
    };

    handleFilter = (e) => {
        this.setState({ filterOption: e.target.value });
        //this.setState({filterOption: 1});
        console.log("ID clicked", this.state.filterOption);
    };

    handleFilterText = (e) => {
        this.setState({ filterText: e.target.value });
        //this.setState({filterOption: 1});
        console.log("tedt changed", this.state.filterText);
    };

    async componentDidMount() {

        
        
        const { data: animals } = await axios.get('http://localhost:8080/api/v1/animals/', { headers: { 'Access-Control-Allow-Origin': true } });
        this.setState({ animals });
        for(let i = 0; i<this.state.animals.length; i++){
            if(this.state.animals[i]["breed"]== null){
                this.state.animals[i]["breed"]="na";

            }
        }
        this.setState({ animals });
        
        


        //const promise = axios.get('https://jsonplaceholder.typicode.com/posts')
    }

    handleRemove =(e, animal)=>{
        
        
        const link = "http://localhost:8080/api/v1/animals/" + animal["animalId"];
        axios.delete(link).then(res => {
            console.log(res);
            console.log(res.data);
          });;

          setTimeout(() => {
            window.location.reload(false);
         }, 500);
        //window.location.reload(false);

        


    };

    render() {
        
        let filtered = this.state.animals;
        if (this.state.filterOption == 1) {
            filtered = 1 ? this.state.animals.filter(m => m["animalId"].toString().toLowerCase().includes(this.state.filterText.toLowerCase())) : this.state.animals;
        } else if (this.state.filterOption == 2) {
            filtered = 1 ? this.state.animals.filter(m => m["name"].toString().toLowerCase().includes(this.state.filterText.toLowerCase())) : this.state.animals;
        } else if (this.state.filterOption == 3) {
            filtered = 1 ? this.state.animals.filter(m => m["breed"].toString().toLowerCase().includes(this.state.filterText.toLowerCase())) : this.state.animals;
        } else if (this.state.filterOption == 4) {
            filtered = 1 ? this.state.animals.filter(m => m["ownerEmail"].toString().toLowerCase().includes(this.state.filterText.toLowerCase())) : this.state.animals;
        } else if (this.state.filterOption == 5) {
            filtered = 1 ? this.state.animals.filter(m => m["status"].toString().toLowerCase().includes(this.state.filterText.toLowerCase())) : this.state.animals;
        } else {
            filtered = this.state.animals;
        }

        // console.log(filtered);
         const user = this.props.match.params.user;
         const uid = this.props.match.params.uid;

         if(user == "s" || user == "i"){
            return <React.Fragment>
            <NavBar user = {user} uid = {uid}/>
            
            <div className="container">
            </div>

            <div className="input-group mb-3">
                <label className="input-group-text" htmlFor="inputGroupSelect01">Search For</label>
                <select onChange={(e) => this.handleFilter(e)} value={this.state.filterOption} className="form-select" id="selectFilter" >
                    <option value="0">No Filter</option>
                    <option value="1">ID</option>
                    <option value="2">Name</option>
                    <option value="3">Breed</option>
                    <option value="4">Owner's Email</option>
                    <option value="5">Status</option>
                </select>
            </div>

            <div className="input-group mb-3">
                < input type="text" id="inputFilter" onChange={(e) => this.handleFilterText(e)} value={this.state.filterText} className="form-control" placeholder="Enter Your Search Term Here" aria-label="Enter Your Search Term Here" aria-describedby="basic-addon2" />

            </div>

            <table className="table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Breed</th>
                        <th>Owner Email</th>
                        <th>Status</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    {filtered.map(animal => (
                        <tr key={animal["animalId"].toString()}>
                            <td>{animal["animalId"].toString()}</td>
                            <td>{(animal["name"] == null) ? 'na' : animal["name"].toString()}</td>
                            <td>{(animal["breed"] == null) ? 'na' : animal["breed"].toString()}</td>
                            <td>{(animal["ownerEmail"] == null) ? 'na' : animal["ownerEmail"].toString()}</td>
                            <td>{(animal["status"] == null) ? 'na' : animal["status"].toString()}</td>
                            {/* <td><Link to={"/" + user + "/animals/" + animal["animalId"].toString()} className="btn btn-primary btn-sm">Details</Link></td> */}
                            <td><Link to={"/"+user + "/" + uid +  "/animals/" + animal["animalId"].toString()} className="btn btn-primary btn-sm">Details</Link></td>
                        </tr>
                    ))}
                </tbody>
            </table>

            <div className="row">
                <div className="col-sm">
                </div>

                <div className="col-sm">
                </div>

                <div className="col-sm">
                </div>

                <div className="col-sm">
                </div>

                <div className="col-sm">
                    
                </div>
            </div>
        </React.Fragment>;

         } else{
            return <React.Fragment>
            <NavBar user = {user} uid = {uid}/>
            
            <div className="container">
            </div>

            <div className="input-group mb-3">
                <label className="input-group-text" htmlFor="inputGroupSelect01">Search For</label>
                <select onChange={(e) => this.handleFilter(e)} value={this.state.filterOption} className="form-select" id="selectFilter" >
                    <option value="0">No Filter</option>
                    <option value="1">ID</option>
                    <option value="2">Name</option>
                    <option value="3">Breed</option>
                    <option value="4">Owner's Email</option>
                    <option value="5">Status</option>
                </select>
            </div>

            <div className="input-group mb-3">
                < input type="text" id="inputFilter" onChange={(e) => this.handleFilterText(e)} value={this.state.filterText} className="form-control" placeholder="Enter Your Search Term Here" aria-label="Enter Your Search Term Here" aria-describedby="basic-addon2" />

            </div>

            <table className="table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Breed</th>
                        <th>Owner Email</th>
                        <th>Status</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    {filtered.map(animal => (
                        <tr key={animal["animalId"].toString()}>
                            <td>{animal["animalId"].toString()}</td>
                            <td>{(animal["name"] == null) ? 'na' : animal["name"].toString()}</td>
                            <td>{(animal["breed"] == null) ? 'na' : animal["breed"].toString()}</td>
                            <td>{(animal["ownerEmail"] == null) ? 'na' : animal["ownerEmail"].toString()}</td>
                            <td>{(animal["status"] == null) ? 'na' : animal["status"].toString()}</td>
                            {/* <td><Link to={"/" + user + "/animals/" + animal["animalId"].toString()} className="btn btn-primary btn-sm">Details</Link></td> */}
                            <td><Link to={"/"+user + "/" + uid + "/animals/" + animal["animalId"].toString()} className="btn btn-primary btn-sm">Details</Link><button onClick={(e) => this.handleRemove(e, animal)} className="btn btn-danger btn-sm">Remove</button></td>
                        </tr>
                    ))}
                </tbody>
            </table>

            <div className="row">
                <div className="col-sm">
                </div>

                <div className="col-sm">
                </div>

                <div className="col-sm">
                </div>

                <div className="col-sm">
                </div>

                <div className="col-sm">
                    <Link to={"/"+user + "/" + uid +  "/animals/isowner"} className="btn btn-secondary">Add Animal</Link>
                </div>
            </div>
        </React.Fragment>;

         }

        
    }
}

export default AnimalManagement;
