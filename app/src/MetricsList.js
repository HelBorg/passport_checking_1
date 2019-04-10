import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';

class MetricsList extends Component {

    constructor(props) {
        super(props);
        this.state = {metricsL: [], isLoading: true};
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        this.setState({isLoading: true});

        fetch('/api/metrics')
            .then(response => response.json())
            .then(data => this.setState({metricsL: data, isLoading: false}));
    }

    async remove(id) {
        await fetch(`/`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedMetrics = [...this.state.metricsL].filter(i => i.id !== id);
            this.setState({groups: updatedMetrics});
        });
    }

    render() {
        const {metricsL, isLoading} = this.state;

        if (isLoading) {
            return <p>Loading...</p>;
        }

        const metricsList = metricsL.map(metrics => {

                return <tr key={metrics.id}>
                    <td>
                        <ButtonGroup>
                            <Button size="sm" color="primary" tag={Link} to={"/metrics/" + metrics.id}>Edit</Button>
                            <Button size="sm" color="danger" onClick={() => this.remove(metrics.id)}>Delete</Button>
                        </ButtonGroup>
                    </td>
                </tr>

            });

        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/metrics/new">Add Group</Button>
                    </div>
                    <h3>My JUG Tour</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="20%">Name</th>
                            <th>Location</th>
                            <th width="10%">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {metricsList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}

export default MetricsList;