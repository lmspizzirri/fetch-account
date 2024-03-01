fetchData = async () => {
    try {
        const response = await fetch('http://172.16.6.127:8080/fetch/create');
        const data = await response.json();
        this.setState({ responseData: data });
    } catch (error) {
        console.error('Erro ao buscar dados da API:', error);
    }
}

export default fetchData;
