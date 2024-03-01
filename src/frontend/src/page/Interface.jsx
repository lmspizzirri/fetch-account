import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import './interface.css';

class Interface extends React.Component {
    state = {
        loginInfo: undefined,
        passwordInfo: undefined,
        deleteInfo: undefined,
        users: [],
    }

    componentDidMount() {
        this.fetchUsers();
    };
    Triunfo2
    fetchUsers = async () => {
        try {
            const response = await fetch('http://172.16.1.210:8080/fetch/accounts',{
                method: 'GET',
                headers: {
                    'Authorization': localStorage.getItem('token'),
                }
            })
            if (!response.ok) {
                throw new Error('Erro ao buscar usuários')
            }
            const data = await response.json();
            this.setState({ users: data });
        } catch (error) {
            console.error('Erro ao buscar usuários: ', error);
        }
    }

    onInputChange = ({ target }) => {
        const { name, value } = target;
        this.setState({
            [name]: value,
        })
    }

    handleClick = async () => {
        try {
            const { loginInfo, passwordInfo } = this.state;
            await fetch('http://172.16.1.210:8080/fetch/create', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': localStorage.getItem('token'),
                },
                body: JSON.stringify({ login: loginInfo, password: passwordInfo }),
            });
            this.setState({
                loginInfo: '',
                passwordInfo: '',
            })
            this.fetchUsers();
        } catch (error) {
            console.error('Erro ao criar usuário: ', error);
        }
    }

    handleClickDelete = async () => {
        try {
            const { deleteInfo } = this.state;
            await fetch('http://172.16.1.210:8080/fetch/delete', {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': localStorage.getItem('token'),
                },
                body: deleteInfo.toString(),
            });
            this.setState({
                deleteInfo: '',
            })
            this.fetchUsers();
        } catch (error) {
            console.error('Erro ao deletar usuário: ', error);
        }
    }

    render() {
        const { loginInfo, passwordInfo, deleteInfo, users } = this.state;
        return (    
            <div className='container'>
                <img src="https://triunfologistica.com.br/novo-site/wp-content/uploads/2019/10/logo-topo.png" alt="Triunfo Logística" />
                <div>
                    <h1>Gerenciamento de Usuários Microsoft</h1>
                    <br />
                    <br />
                    <h2>Cadastro de Conta</h2>
                    <input
                        placeholder="Login"
                        name="loginInfo"
                        type="text"
                        onChange={this.onInputChange}
                        value={loginInfo}
                    />
                    <br />
                    <input
                        placeholder="Password"
                        name="passwordInfo"
                        type="password"
                        onChange={this.onInputChange}
                        value={passwordInfo}
                    />
                    <br />
                    <button
                        type='submit'
                        onClick={this.handleClick}
                    >
                        Submit
                    </button>
                    <br />
                    <br />
                    <h2>Deletar Conta</h2>
                    <input
                        placeholder="Id"
                        name="deleteInfo"
                        type="text"
                        onChange={this.onInputChange}
                        value={deleteInfo}
                    />
                    <br />
                    <button
                        type='submit'
                        onClick={this.handleClickDelete}
                    >
                        Delete
                    </button>
                </div>
                <br />
                <table style={{ borderCollapse: 'collapse', width: 'auto' }}>
                    <thead>
                        <tr>
                            <th style={{ border: '1px solid black', padding: '8px', textAlign: 'left' }}>ID</th>
                            <th style={{ border: '1px solid black', padding: '8px', textAlign: 'left' }}>Login</th>
                            <th style={{ border: '1px solid black', padding: '8px', textAlign: 'left' }}>Senha</th>
                        </tr>

                    </thead>
                    <tbody>
                        {users.map(user => (
                            <tr key={user.id}>
                                <td style={{ border: '1px solid black', padding: '8px', textAlign: 'left' }}>{user.id}</td>
                                <td style={{ border: '1px solid black', padding: '8px', textAlign: 'left' }}>{user.login}</td>
                                <td style={{ border: '1px solid black', padding: '8px', textAlign: 'left' }}>{user.password}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        )
    }
}

export default Interface;