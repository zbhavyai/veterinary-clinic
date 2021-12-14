import React from 'react';

import axios from 'axios';
import NavBar from './navbar';

class RequestManagement extends React.Component {
    state = {
        animals: [],
        //animals: getAnimals(),
        filterOption: 0,
        filterText: "",
        pageSize: 10,
        alertmsg:""


    };

    handleInputText =(e)=>{
      this.setState({inputText:e.target.value});
      //this.setState({filterOption: 1});
      console.log("tedt changed", this.state.filterText);

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

    handleAccept =(e, animal)=>{

        const user = this.props.match.params.user;
        if(user == "t"){
            //animal["status"]="TECHNICIAN_APPROVAL";
            const status = {"requestStatus": "READY"};
            const link = "http://localhost:8080/api/v1/animals/" + animal["animalId"];
            axios.put(link, status,{headers:{}});
            window.location.reload(false);

        } else if(user =='a'){
            //animal["status"]="ACCEPTED_BY_ADMIN";
            const status = {"requestStatus": "ACCEPT_BY_ADMIN"};
            const link = "http://localhost:8080/api/v1/animals/" + animal["animalId"];
            axios.put(link, status,{headers:{}});
            window.location.reload(false);

        }
        this.setState({alertmsg:"  Request is Accepted for: " + animal["name"]});





    };

    handleReject =(e, animal)=>{
        this.setState({alertmsg:"  Request is rejected for: " + animal["name"]});
        //animal["status"]="GREEN";
        const status = {"requestStatus": "REJECT"};
        const link = "http://localhost:8080/api/v1/animals/" + animal["animalId"];
        axios.put(link, status,{headers:{}});
        window.location.reload(false);
        this.setState({alertmsg:"  Request is rejected for: " + animal["name"]});





    };






    async componentDidMount() {

      const {data: animals} = await axios.get('http://localhost:8080/api/v1/animals/', {headers: {'Access-Control-Allow-Origin': true,},});

      this.setState({animals});



      //const promise = axios.get('https://jsonplaceholder.typicode.com/posts')

  }




    render() {
        const user = this.props.match.params.user;
        const uid = this.props.match.params.uid;
        let filtered = this.state.animals;
        if(user == 't'){
            filtered = 1?this.state.animals.filter(m=>m["requestStatus"].toString().includes("ACCEPT_BY_ADMIN") ):this.state.animals;

        }else if(user =='a'){
            filtered = 1?this.state.animals.filter(m=>m["requestStatus"].toString().includes("REQUESTED") ):this.state.animals;
        }

        let alert = this.state.alertmsg;

        if(filtered.length==0){
            alert = "You have no more requests...";
        }






        return <React.Fragment>
                <NavBar user = {user} uid = {uid}/>
                <div className="container">

                </div>







        <table className="table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Breed</th>
                    <th>Owner</th>
                    <th>Request Status</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                {filtered.map(animal =>(
                    <tr key = {animal["animalId"].toString()}>
                    <td>{animal["animalId"].toString()}</td>
                    <td>{(animal["name"]==null) ? 'na' : animal["name"].toString()}</td>
                    <td>{(animal["breed"]==null) ? 'na' : animal["breed"].toString()}</td>
                    <td>{(animal["ownerName"]==null) ? 'na' : animal["ownerName"].toString()}</td>
                    <td>{(animal["requestStatus"]==null) ? 'na' : animal["requestStatus"].toString()}</td>

                    <td><button onClick={(e) => this.handleAccept(e, animal)} className="btn btn-primary btn-sm">Accept</button><button onClick={(e) => this.handleReject(e, animal)} className="btn btn-primary btn-sm">Reject</button></td>

                    </tr>

                ))}




            </tbody>
        </table>
        <div className="row">
                      <div className="col-sm">
                      {alert}
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



      </React.Fragment>
        ;
    }
}

export default RequestManagement;