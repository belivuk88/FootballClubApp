import React from "react";
import { Button, Form } from "react-bootstrap";

import KlubAxios from "../../apis/KlubAxios";

class Transfer extends React.Component {
    constructor(props) {
        super(props);

        let igrac = {
            imePrezime: "",
            pozicija: "",
            brojDresa: 0,
            datum: "",
            naProdaju: false,
            klubId: -1,
        };

        let transfer = {
            cena: 0,
            igracId: this.props.match.params.id,
            klubId: this.props.match.params.id,
        };

        this.state = {
            igrac: igrac,
            transfer: transfer,
            igraci: [],
            klubovi: [],
            transferi: [],
            avaliable: false,
        };
    }

    componentDidMount(){
        this.getData();
    }

    async getData(){
        await this.getIgraci();
        await this.getKlubovi();
        await this.getTransferi();
    }

    async getTransferi(){
        try{
            let transfer = this.state.transfer;
            let transferDTO = {
                cena: transfer.cena,
                igracId: transfer.igracId,
                klubId: transfer.klubId
            }
            let result = await KlubAxios.get("/transferi", transferDTO);
            if(result && result.status === 200){
                this.setState({
                    transferi: result.data,
                });
            }
            }catch(error) {
                alert("Nije uspelo dobavljanje.");
            }

        }

    async getIgraci(){
        try{
            let igrac = this.state.igrac;
            let igracDTO = {
                igrac: igrac.imePrezime,
                igrac: igrac.pozicija,
                igrac: igrac.datum,
                igrac: igrac.brojDresa,
                igrac: igrac.naProdaju,
                igrac: igrac.klubId
            }
            let result = await KlubAxios.get("/igraci", igracDTO);
            if(result && result.status === 200){
                this.setState({
                    igraci: result.data,
                });
            }
        }catch(error){
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

    valueInputChange(event) {
        let control = event.target;

        let name = control.name;
        let value = control.value;

        let transfer = this.state.transfer;
        transfer[name] = value;

        this.setState({ transfer: transfer });
    }

    async prelazak(){
        try{
            await KlubAxios.post(`/igraci/${this.props.match.params.id}/transfer`, this.state.transfer);
            this.getIgraci(0);
            this.props.history.push("/igraci");
        }catch(error) {
            alert("Nije moguce pormeniti stanje.");
        }
    }

    render(){
        return(
            <div>
                <h1>Transfer</h1>
                <Form>
                    <Form.Group>
                        <Form.Label>Cena</Form.Label>
                        <Form.Control
                        onChange={(event) => this.valueInputChange(event)}
                        name="cena"
                        value={this.state.transfer.cena}
                        as="input">
                        </Form.Control>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Klub</Form.Label>
                        <Form.Control
                        onChange={(event) => this.valueInputChange(event)}
                        name= "klubId"
                        value= {this.state.transfer.klubId}
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
                    <Button disabled={this.state.transfer.cena === 1}
                    variant="success" onClick={() => this.prelazak()}>
                        Transfer
                    </Button>
                </Form>
            </div>
        );
    }

    }

    export default Transfer;