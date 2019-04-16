import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';

class MetricsList extends Component {

    constructor(props) {
        super(props);
        this.state = {metrics: [], isLoading: true};
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        this.setState({isLoading: true});

        fetch('api/metrics')
            .then(response => response.json())
            .then(data => this.setState({metrics: data, isLoading: false}));
    }

    async remove(id) {
        await fetch(`/api/`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedMetrics = [...this.state.metrics];
            this.setState({metrics: updatedMetrics});
        });
    }

    render() {
        const {metrics, isLoading} = this.state;

        if (isLoading) {
            return <p>Loading...</p>;
        }

        const metricList = metrics.map(metric => {



            const address = `${metric.address || ''} ${metric.city || ''} ${metric.stateOrProvince || ''}`;
            return <tr key={metric.id}>
                <td style={{whiteSpace: 'nowrap'}}>{metric.name}</td>
                <td>{address}</td>
                <td>{metric.events.map(event => {
                    return <div key={event.id}>{new Intl.DateTimeFormat('en-US', {
                        year: 'numeric',
                        month: 'long',
                        day: '2-digit'
                    }).format(new Date(event.date))}: {event.title}</div>
                })}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"/metrics/" + metric.id}>Edit</Button>
                        <Button size="sm" color="danger" onClick={() => this.remove(metric.id)}>Delete</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });

        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/metrics/new">Add metric</Button>
                    </div>
                    <h3>My JUG Tour</h3>
                    <Table className="mt-4">
                        <tbody>
                        {metricList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}

export default MetricList;