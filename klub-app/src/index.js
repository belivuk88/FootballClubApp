import React from "react";
import ReactDOM from "react-dom";
import { Route, Link, HashRouter as Router, Switch } from "react-router-dom";
import { Container, Navbar, Nav, Button } from "react-bootstrap";
import Home from "./components/Home";
import NotFound from "./components/NotFound";
import Login from "./components/login/Login";
import Igrac from "./components/klub/Igrac";
import { logout } from "./services/auth";
import AddIgrac from "./components/klub/AddIgrac";
import Transfer from "./components/klub/Transfer";


class App extends React.Component {
    render(){
        return(
            <div style = {{
                backgroundImage: `url(${process.env.PUBLIC_URL + '/ChampionsLeague.jpg'})`,
                backgroundPosition: 'center',
                backgroundRepeat: 'no-repeat',
                width: '100vw',
                height: '100vh'}}>
            <div>
                <Router>
                    <Navbar bg= "dark" variant ="dark" expand>
                        <Navbar.Brand as = {Link} to = "/">
                        <img src="/ChampionsLogo.jpg"
                            width="60"
                            height="30"
                            className="d-inline-block align-top"
                            alt="React Bootstrap logo"></img>
                        </Navbar.Brand>
                        <Nav className = "mr-auto">
                            <Nav.Link as={Link} to= "/igraci">
                                Igraci
                            </Nav.Link>
                        </Nav>

                        {window.localStorage['jwt'] ?
                        <Button onClick = {()=>logout()}>Log out</Button> :
                        <Nav.Link as={Link} to="/login">Log in</Nav.Link>
                        }    
                    </Navbar>
                    <Container style={{marginTop:25}}>
                        <Switch>
                            <Route exact path = "/" component = {Home}/>
                            <Route exact path = "/igraci" component = {Igrac}/>
                            <Route exact path = "/igraci/add" component = {AddIgrac}/>
                            <Route exact path = "/igraci/transfer/:id" component = {Transfer}/>
                            <Route exact path = "/login" component = {Login}/>
                            <Route component = {NotFound}/> 
                        </Switch>
                    </Container>
                </Router>
            </div>
            </div>
        );
    }
}

ReactDOM.render(<App/>, document.querySelector("#root"));