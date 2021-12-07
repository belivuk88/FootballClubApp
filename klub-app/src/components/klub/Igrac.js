import React from "react";
import { Table, Button, Form, ButtonGroup, Nav, Navbar } from "react-bootstrap";
import KlubAxios from "../../apis/KlubAxios";

class Igrac extends React.Component {
    constructor(props) {
        super(props);

        let igrac = {
            imePrezime: "",
            pozicija: "",
            brojDresa: 0,
            datum: "",
            naProdaju: false,
            klubId: this.props.match.params.id,
        
        };

        this.state = {
            igrac: igrac,
            igraci: [],
            klubovi: [],
            search: {pozicija: "", klubId: -1},
            pageNo: 0,
            totalPages: 2,

        };

    }

    componentDidMount(){
        this.getData();
    }

    async getData(){
        await this.getKlubovi();
        await this.getIgraci(0);
    }

    async getIgraci(page){
        let config = {
            params: {
                pageNo: page
            }
        };

        if(this.state.search.pozicija != "") {
            config.params.pozicija = this.state.search.pozicija;
        }
        if(this.state.search.klubId != -1) {
            config.params.klubId = this.state.search.klubId;
        }
        try{
            let result = await KlubAxios.get("/igraci", config);
            if (result && result.status === 200){
                this.setState({
                    pageNo: page,
                    igraci: result.data,
                    totalPages: result.headers["total-pages"],
                });
            }
        }catch (error) {
            alert("Nije uspelo dobavljanje.");
        }
    }

    async getKlubovi(){
        try{
            let result = await KlubAxios.get("/klubovi");
            if(result && result.status === 200) {
                this.setState({
                    klubovi: result.data,
                });
            }
        }catch (error) {
            alert("Nije uspelo dobavljanje.");
        }
    }

    goToTransfer(igracId) {
        this.props.history.push("igraci/transfer/" + igracId);
    }

    goToAdd() {
        this.props.history.push("/igraci/add/");
    }

    async doDelete(igracId){
        try{
            await KlubAxios.delete("/igraci/" + igracId);
            this.getIgraci(0);
        }catch (error) {
            alert("Nije uspelo brisanje.");
        }
    }

    searchValueInputChange(event){
        let control = event.target;

        let name = control.name;
        let value = control.value;

        let search = this.state.search;
        search[name] = value;

        this.setState({ search: search });
    }

    doSearch(){
        this.getIgraci(0);
    }

    hiddenForm(){
        const curentState = this.state.igrac.naProdaju;

        let igrac = this.state.igrac;
        igrac.naProdaju = !curentState;
        this.setState({ igrac: igrac });
    }

    check(igrac){
        if( !igrac.naProdaju || igrac.naProdaju === false){

        return true;
        }
        return false;
    }

    render() {
        return (
            <div>

                <h1>Igraci</h1>

                <Form style ={{ marginTop: 35 }}>
                    <Form.Group>
                        <Form.Label>Pozicija</Form.Label>
                        <Form.Control
                            value={this.state.search.pozicija}
                            name="pozicija"
                            as="input"
                            onChange={(e) => this.searchValueInputChange(e)}
                            onKeyUp={() => this.doSearch()}>
                        </Form.Control>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Naziv kluba</Form.Label>
                        <Form.Control
                            onChange={(e) => this.searchValueInputChange(e)}
                            onClick={() => this.doSearch()}
                            value={this.state.search.klubId}
                            name="klubId"
                            as="select"
                        >
                            <option value={-1}></option>
                            {this.state.klubovi.map((klub) => {
                                return (
                                    <option value={klub.id} key={klub.id}>
                                        {klub.naziv}
                                    </option>
                                );
                            })}
                            </Form.Control>
                    </Form.Group>
                </Form>
                <Navbar>
                    <Nav className= "mr-auto">
                        <ButtonGroup style={{ marginTop: 25 }}>
                            <Button variant= "success" onClick={() => this.goToAdd()}>
                                Kreiraj igraca
                            </Button>
                        </ButtonGroup>
                    </Nav>
                    <Nav>
                    <ButtonGroup style={{ marginTop: 25 }}>
                        <Button
                        disabled={this.state.pageNo== 0} onClick={() => this.getIgraci(this.state.pageNo - 1)}>
                            Prethodna
                        </Button>
                        <Button 
                        disabled={this.state.pageNo==this.state.totalPages - 1} onClick={() => this.getIgraci(this.state.pageNo + 1)}>
                            Sledeća
                        </Button>
                    </ButtonGroup>
                    </Nav>
                </Navbar>

                <Table bordered striped variant="dark" style={{ marginTop: 5 }}>
                    <thead className="thead-dark">
                        <tr>
                            <th>Ime i prezime</th>
                            <th>Pozicija</th>
                            <th>Broj dresa</th>
                            <th>Datum rodjenja</th>
                            <th>Na prodaju</th>
                            <th>Naziv kluba</th>
                            <th colSpan={2}>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {this.state.igraci.map((igrac) => {
                            return (
                                <tr key={igrac.id}>
                                    <td>{igrac.imePrezime}</td>
                                    <td>{igrac.pozicija}</td>
                                    <td>{igrac.brojDresa}</td>
                                    <td>{igrac.datum}</td>
                                    <td>{igrac.naProdaju.toString()}</td>
                                    <td>{igrac.klubNaziv}</td>
                                    <td>
                                        <Button
                                        hidden = {this.check(igrac)}
                                        variant="info"
                                        className = {this.state.igrac.naProdaju ? 'hidden' : null}
                                        onChange={() => this.hiddenForm()}
                                        onClick={() => this.goToTransfer(igrac.id)}
                                        >
                                            Transfer

                                        </Button>
                                        <Button
                                            variant = "danger"
                                            onClick={() => this.doDelete(igrac.id)}
                                            style={{ marginLeft: 5 }}>

                                                Obriši

                                        </Button>
                                    </td>
                                </tr>
                            );
                        })}
                    </tbody>
                </Table>
            </div>
        );
    }

}

export default Igrac;