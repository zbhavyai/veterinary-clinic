import React from 'react';
import axios from "axios";


import NavBar from './navbar';

class AnimalStatus extends React.Component {
    state = {
        animal: {},
        weights: [],
        owner: {},
        imageUrl: process.env.PUBLIC_URL + '/toobad.png',
        apiphotos: [],
        photos:[],
    };

    styles = {
        width: 18 + 'rem'
    };

    handleStatus =(e, statusx)=>{
        const id = this.props.match.params.id;
       
        const status = {"status": statusx};
        const link = "http://localhost:8080/api/v1/animals/" + id;
        axios.put(link, status,{headers:{}});

        setTimeout(() => {
            window.location.reload(false);
         }, 500);
        
        





    };

    async componentDidMount() {
        const id = this.props.match.params.id;

        var animalIdUrl = "http://localhost:8080/api/v1/animals/" + id
        const { data: animal } = await axios.get(animalIdUrl, { headers: { 'Access-Control-Allow-Origin': true, }, });
        this.setState({ animal });

        var animalWeightUrl = "http://localhost:8080/api/v1/animals/" + id + "/weights"
        const { data: weights } = await axios.get(animalWeightUrl, { headers: { 'Access-Control-Allow-Origin': true, }, });
        this.setState({ weights });

        var ownerId = this.state.animal["ownerId"];
        var ownerUrl = "http://localhost:8080/api/v1/owners/" + ownerId;
        const { data: owner } = await axios.get(ownerUrl, { headers: { 'Access-Control-Allow-Origin': true, }, });
        this.setState({ owner });

        var animalphotoUrl = "http://localhost:8080/api/v1/animals/" + id + "/photos"
        const { data: apiphotos } = await axios.get(animalphotoUrl, { headers: { 'Access-Control-Allow-Origin': true, }, });
        this.setState({ apiphotos });
        
        
        var photourl = null;
        var paray = [];
        for (var i = 0; i< this.state.apiphotos.length;i++){
            photourl = "http://localhost:8080/api/v1/animals/" + id+ "/photos/" + this.state.apiphotos[i]["photoId"];
            
            paray.push(photourl);

        }

        this.setState({ photos: paray});

        if(this.state.photos.length>0){
            this.setState({
                imageUrl: this.state.photos[0]
            })
        }
        

        console.log(this.state.photos.length)
    }

    handleChange(event) {

        //const promise = axios.get('https://jsonplaceholder.typicode.com/posts')
    }

    render() { 
        const user = this.props.match.params.user;
        
        const uid = this.props.match.params.uid;

        return <React.Fragment>
            
            <NavBar user = {user} uid = {uid}/>

            <div className="row">
                <div className="col-sm">
                    <img src={this.state.imageUrl} width="300" 
                                height="300" alt="" />

                </div>
                <div className="col-sm">
                    <div className="jumbotron jumbotron-fluid">
                        <div className="container">
                            <h1 className="display-4">Basic Details</h1>
                            <p className="lead">Animal ID: {this.state.animal["animalId"]}</p>
                                <p className="lead">Name: {this.state.animal["name"]}</p>
                                <p className="lead">Breed: {this.state.animal["breed"]}</p>
                                <p className="lead">Age: {this.state.animal["age"]}</p>
                                <p className="lead">Sex: {this.state.animal["sex"]}</p>
                                <p className="lead">Status: {this.state.animal["status"]}</p>
                        </div>
                    </div>
                </div>

            </div>

            <div className="row">
                <div className="jumbotron jumbotron-fluid">
                    <div className="container">
                        <h1 className="display-4">Change Status to:</h1>
                        <div>
                        <button onClick={(e) => this.handleStatus(e, "GREEN")} className="btn btn-primary">Green</button><button onClick={(e) => this.handleStatus(e, "YELLOW")} className="btn btn-primary ">Yellow</button><button onClick={(e) => this.handleStatus(e, "RED")} className="btn btn-primary ">Red</button><button onClick={(e) => this.handleStatus(e, "INACTIVE")} className="btn btn-primary">Inactive</button>
                        </div>
                        
                    </div>
                </div>

            </div>

        </React.Fragment>;
    }
}
 
export default AnimalStatus;