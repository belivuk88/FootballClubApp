import React from "react";
import { Button, Form } from "react-bootstrap";
import KlubAxios from "../../apis/KlubAxios";


class AddIgrac extends React.Component {
    constructor(props){
        super(props);

        let igrac = {
            imePrezime: "",
            pozicija: "",
            brojDresa: 0,
            datum: "",
            naProdaju: false,
            klubId: -1,
        };

        this.state = {
            igrac: igrac,
            igraci: [],
            klubovi: []
        };
    }

    componentDidMount(){
        this.getData();
    }

    async getData(){
        await this.getKlubovi();
        await this.getIgraci();
    }

    async getIgraci(){
        try{
            let igrac = this.state.igrac;
            let igracDTO = {
                imePrezime: igrac.imePrezime,
                pozicija: igrac.pozicija,
                brojDresa: igrac.brojDresa,
                datum: igrac.datum,
                naProdaju: igrac.naProdaju,
                klubId: igrac.klubId
            }
            let result = await KlubAxios.get("/igraci/pozicija", igracDTO);
            if(result && result.status === 200){
                this.setState({
                    igraci: result.data
                });
            }
        } catch(error){
            alert("Nije uspelo dobavljanje.");
        }
    }

    async getKlubovi(){
        try{
            let result = await KlubAxios.get("/klubovi");
            if(result && result.status === 200){
                this.setState({
                    klubovi: result.data,
                });
            }
        } catch(error){
            alert("Nije uspelo dobavljanje.");
        }
    }

    async doAdd(){
        try{
            await KlubAxios.post("/igraci", this.state.igrac);

            this.props.history.push("/igraci");
        }catch(error) {
            alert("Nije uspelo dobavljanje.")
        }
    }

    addValueInputChange(event) {
        let control = event.target;

        let name = control.name;
        let value = control.value;

        let igrac = this.state.igrac;
        igrac[name] = value;

        this.setState({ igrac: igrac });
    }

    addValueInputDate(event) {
        let control = event.target;
        let datum = control.value;

        if( new Date(datum) === new Date("yyyy/MM/dd","dd.MM.yyyy.")){
            return;
        }
        let igrac = this.state.igrac;
        igrac.datum = datum

        this.setState({ datum: datum });
    }

    handleChange(){
        const currentState = this.state.igrac.naProdaju;

        let igrac = this.state.igrac;
        igrac.naProdaju = !currentState;
        this.setState({ igrac: igrac });
      }

      check(igrac){
        try{
        if( new Date(igrac.datum) === new Date("yyyy/MM/dd","dd.MM.yyyy.")){
            
            return true;
        }
        return false;
    }catch (error){
        alert("Nije uspelo dodavanje.");
    }
      } 

    render(){
        return (
            <div>
                <h1>Kreiraj igraca</h1>
                <Form>
                    <Form.Group>
                        <Form.Label>Ime i prezime</Form.Label>
                        <Form.Control
                        onChange={(event) => this.addValueInputChange(event)}
                        name= "imePrezime"
                        value={this.state.igrac.imePrezime}
                        as="input">
                        </Form.Control>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Pozicija</Form.Label>
                        <Form.Control
                        onChange={(event) => this.addValueInputChange(event)}
                        name= "pozicija"
                        value={this.state.igrac.pozicija}
                        as="select">
                            <option value={-1}></option>
                            {this.state.igraci.map((igrac) => {
                                return(
                                    <option value={igrac.pozicija} key={igrac.id}>
                                        {igrac.pozicija}
                                    </option>
                                )
                            })};
                        </Form.Control>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Broj dresa</Form.Label>
                        <Form.Control
                        onChange={(event) => this.addValueInputChange(event)}
                        name= "brojDresa"
                        value={this.state.igrac.brojDresa}
                        as="input">
                        </Form.Control>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Datum rođenja</Form.Label>
                        <Form.Control
                        onChange={(event) => this.addValueInputChange(event)}
                        name= "datum"
                        value={this.state.igrac.datum}
                        as="input">
                        </Form.Control>
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="formBasicCheckbox">
                    <Form.Check type="checkbox" label="Na prodaju"
                        onChange={() => this.handleChange()}
                        name= "naProdaju"
                        value={this.state.igrac.naProdaju}
                        as="input">
                        </Form.Check>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Klub</Form.Label>
                        <Form.Control
                        onChange={(event) => this.addValueInputChange(event)}
                        name="klubId"
                        value={this.state.igrac.klubId}
                        as= "select">
                            <option value={-1}></option>
                            {this.state.klubovi.map((klub) => {
                                return(
                                    <option value={klub.id} key={klub.id}>
                                        {klub.naziv}
                                    </option>
                                )
                            })};
                        </Form.Control>
                    </Form.Group>
                    <Button variant="success" onClick={() => this.doAdd()}>
                        Kreiraj igraca
                    </Button>
                </Form>
            </div>
        );
    }

}

export default AddIgrac;