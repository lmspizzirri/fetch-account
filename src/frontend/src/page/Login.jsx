import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';


const Login = () => {
    const [formData, setFormData] = useState({
        username: '',
        password: '',
    });
    const navigate = useNavigate();
    const { username, password } = formData;

    const onInputChange = (event) => {
        const { name, value } = event.target;
        setFormData({
            ...formData,
            [name]: value,
        });
    };

    const handleClick = async () => {
        try {
            const response = await fetch('http://172.16.1.210:8080/auth/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ username, password })
            });
            if (response.ok) {
                const { token } = await response.json();
                localStorage.setItem('token', token);
                navigate('/painel');
            }       
        } catch (error) {
            console.error('Ocorreu um erro durante a solicitação de login:', error);
        }
    };

    return (
        <div className='container'>
            <input
                type="text"
                placeholder="username"
                name="username"
                onChange={onInputChange}
                value={username}
            />
            <input
                type="password"
                placeholder="password"
                name="password"
                onChange={onInputChange}
                value={password}
            />
            <button
                type='submit'
                onClick={handleClick}
            >
                Submit
            </button>
        </div>
    );
};

Login.propTypes = {
};

export default Login;
