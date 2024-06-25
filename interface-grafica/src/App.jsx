import { useState } from 'react'
import './App.css'
import axios from 'axios'

const URL = 'http://localhost:8080/';

const defaultState = {
  inputDisabled: true,
  buttonDisabled: true,
  labelText: '...',
  endOfUrl: '',
  reponseData: '...',
  inputValue: '',
};

function App() {
  const [state, setState] = useState(defaultState)

  const doGetRequest = async (endOfUrl) => {
    try {
      const response = await axios.get(URL+endOfUrl)
      setState({ ...state, inputDisabled: true, buttonDisabled: true, reponseData: JSON.stringify(response.data), labelText: defaultState.labelText})
    } catch (error) {
      setState({ ...state, inputDisabled: true, buttonDisabled: true, reponseData: error.message, labelText: defaultState.labelText})
    }
  }

  const handleDeleteClick = async () => {        
    setState({ ...state, inputDisabled: false, buttonDisabled: false, labelText: 'Informe o nome do funcionário a ser excluído e confirme:', endOfUrl: 'delete/' })
  }

  const handleAumentarSalarioClick = async () => {
    setState({ ...state, inputDisabled: true, buttonDisabled: false, labelText: 'Confirme aumento de salário:', endOfUrl: 'aumenta-salario' })
  }

  const handleAniversariosClick = async () => {
    setState({ ...state, inputDisabled: false, buttonDisabled: false, labelText: 'Insira os meses (números, separados por vírgula) e confirme:', endOfUrl: 'aniversario/?months=' })
  }

  const handleVerTodosClick = async () => {
    await doGetRequest('')
  }

  const handleAgruparPorFuncaoClick = async () => {
    await doGetRequest('funcoes')
  }

  const handleMaisVelhoClick = async () => {
    await doGetRequest('mais-velho')
  }

  const handleOrdemAlfabeticaClick = async () => {
    await doGetRequest('ordenada')
  }

  const handleTotalSalariosClick = async () => {
    await doGetRequest('total')
  }

  const handleSalariosMinimosClick = async () => {
    await doGetRequest('salarios-min')
  }

  const handleConfirm = async () => {
    switch (state.endOfUrl) {
      case 'delete/':
        try {
          await axios.delete(URL+state.endOfUrl+state.inputValue)
          setState({ ...state, isDisabled: true, reponseData: 'Funcionário excluído com sucesso!', endOfUrl: ''})
        } catch (error) {
          setState({ ...state, isDisabled: true, reponseData: error.message, endOfUrl: '' })
        }
        break;
      case 'aumenta-salario':
        try {
          await axios.patch(URL+state.endOfUrl)
          setState({ ...state, isDisabled: true, reponseData: 'Salários aumentados com sucesso!', endOfUrl: ''})
        } catch (error) {
          setState({ ...state, isDisabled: true, reponseData: error.message, endOfUrl: '' })
        }
        break;
      case 'aniversario/?months=':
        try {
          const response = await axios.get(URL+state.endOfUrl+state.inputValue)
          setState({ ...state, inputDisabled: true, buttonDisabled: true, reponseData: JSON.stringify(response.data), labelText: 'Aniversariantes:', endOfUrl: ''})
        } catch (error) {
          setState({ ...state, inputDisabled: true, buttonDisabled: true, reponseData: error.message, labelText: defaultState.labelText, endOfUrl: ''})
        }
        break;
      default:
        break;
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
        <button onClick={handleAumentarSalarioClick}>
          Aumentar salário(10%)
        </button>
        <button onClick={handleAgruparPorFuncaoClick}>
          Agrupar por função
        </button>
        <button onClick={handleAniversariosClick}>
          Aniversários
        </button>
        <button onClick={handleMaisVelhoClick}>
          Mostrar mais velho
        </button>
        <button onClick={handleOrdemAlfabeticaClick}>
          Ver todos em ordem alfabética
        </button>
        <button onClick={handleTotalSalariosClick}>
          Total dos salários
        </button>
        <button onClick={handleSalariosMinimosClick}>
          Salários Mínimos
        </button>
      </div>
      <label htmlFor="myInput">{state.labelText}
        <input id="myInput" type="text" disabled={state.inputDisabled} onChange={handleInputChange}/>
        <button disabled={state.buttonDisabled} onClick={handleConfirm}>Confirmar</button>
      </label>      
      <div className="exibicao">
        {state.reponseData}    
      </div>
    </>
  )
}

export default App
