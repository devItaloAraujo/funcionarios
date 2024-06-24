import { useState } from 'react'
import './App.css'
import axios from 'axios'

const URL = 'http://localhost:8080/';

const defaultState = {
  isDisabled: true,
  labelText: 'Informação adicional:',
  endOfUrl: '',
  reponseData: '...',
  inputValue: '',
};

function App() {
  const [state, setState] = useState(defaultState)

  const handleVerTodosClick = async () => {
    try {
      const response = await axios.get(URL)
      setState({ ...state, isDisabled: true, reponseData: JSON.stringify(response.data), labelText: 'Informação adicional:'})
    } catch (error) {
      setState({ ...state, isDisabled: true, reponseData: error.message, labelText: 'Informação adicional:'})
    }
  }

  const handleDeleteClick = async () => {        
      setState({ ...state, isDisabled: false, labelText: 'Informe o nome do funcionário a ser excluído:', endOfUrl: 'delete/' })
  }

  const handleConfirm = async () => {
    try {
      await axios.delete(URL+state.endOfUrl+state.inputValue)
      setState({ ...state, isDisabled: true, reponseData: 'Funcionário excluído com sucesso!' })
    } catch (error) {
      setState({ ...state, isDisabled: true, reponseData: error.message })
    }
  }

  const handleInputChange = (event) => {
    setState({...state, inputValue: event.target.value})
  }
    
  return (
    <>
      <h1>ERP Funcionários</h1>
      <div className="card">
        <button onClick={handleVerTodosClick}>
          Ver todos
        </button>
        <button onClick={handleDeleteClick}>
          Deletar
        </button>
        <button>
          Aumentar salário(10%)
        </button>
        <button>
          Agrupar por função
        </button>
        <button>
          Aniversários
        </button>
        <button>
          Mostrar mais velho
        </button>
        <button>
          Ver todos em ordem alfabética
        </button>
        <button>
          Total dos salários
        </button>
        <button>
          Salários Mínimos
        </button>
      </div>
      <label htmlFor="myInput">{state.labelText}
        <input id="myInput" type="text" disabled={state.isDisabled} onChange={handleInputChange}/>
        <button disabled={state.isDisabled} onClick={handleConfirm}>Confirmar</button>
      </label>      
      <div className="exibicao">
        {state.reponseData}    
      </div>
    </>
  )
}

export default App
