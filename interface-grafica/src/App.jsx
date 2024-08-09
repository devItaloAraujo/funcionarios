import { useEffect, useState } from 'react'
import './App.css'
import axios from 'axios'
import Loader from './components/Loader';
import MotionButton from './components/MotionButton';

// const URL = 'https://italo-erp-funcionarios.fly.dev/';
const URL = 'http://localhost:8080/';

const defaultState = {
  inputDisabled: true,
  buttonDisabled: true,
  labelText: '...',
  endOfUrl: '',
  responseData: '...',
  inputValue: '',
};

function App() {
  const [state, setState] = useState(defaultState)

  const doGetRequest = async (endOfUrl) => {
    try {
      const response = await axios.get(URL+endOfUrl)
      setState({ ...state, inputDisabled: true, buttonDisabled: true, responseData: response.data, labelText: defaultState.labelText, endOfUrl: endOfUrl})
    } catch (error) {
      setState({ ...state, inputDisabled: true, buttonDisabled: true, responseData: error.message, labelText: defaultState.labelText, endOfUrl: endOfUrl})
    }
  }

    useEffect(() => {
      doGetRequest('')
    }
  , [])

  const handleDeleteClick = () => {        
    setState({ ...state, inputDisabled: false, buttonDisabled: false, labelText: 'Informe o nome do funcionário a ser excluído e confirme:', endOfUrl: 'delete/' })
  }

  const handleAumentarSalarioClick = () => {
    setState({ ...state, inputDisabled: true, buttonDisabled: false, labelText: 'Confirme aumento de salário:', endOfUrl: 'aumenta-salario' })
  }

  const handleAniversariosClick = () => {
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
          setState({ ...state, isDisabled: true, responseData: 'Funcionário excluído com sucesso!', endOfUrl: ''})
        } catch (error) {
          setState({ ...state, isDisabled: true, responseData: error.message, endOfUrl: '' })
        }
        break;
      case 'aumenta-salario':
        try {
          await axios.patch(URL+state.endOfUrl)
          setState({ ...state, isDisabled: true, responseData: 'Salários aumentados com sucesso!', endOfUrl: ''})
        } catch (error) {
          setState({ ...state, isDisabled: true, responseData: error.message, endOfUrl: '' })
        }
        break;
      case 'aniversario/?months=':
        try {
          const response = await axios.get(URL+state.endOfUrl+state.inputValue)
          setState({ ...state, inputDisabled: true, buttonDisabled: true, responseData: response.data, labelText: 'Aniversariantes:', endOfUrl: ''})
        } catch (error) {
          setState({ ...state, inputDisabled: true, buttonDisabled: true, responseData: error.message, labelText: defaultState.labelText, endOfUrl: ''})
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
          <MotionButton
            onClick={handleVerTodosClick}
            color="#3535f1">
            Ver todos
          </MotionButton>
          <MotionButton
            onClick={handleAgruparPorFuncaoClick}
            color="#3535f1">
            Agrupar por função
          </MotionButton>
          <MotionButton
            onClick={handleMaisVelhoClick}
            color="#3535f1">
            Mostrar mais velho
          </MotionButton>
          <MotionButton
            onClick={handleOrdemAlfabeticaClick}
            color="#3535f1">
            Ver todos em ordem alfabética
          </MotionButton>
          <MotionButton
            onClick={handleTotalSalariosClick}
            color="#3535f1">
            Total dos salários
          </MotionButton>
          <MotionButton
            onClick={handleSalariosMinimosClick}
            color="#3535f1">
            Salários Mínimos
          </MotionButton>
          <MotionButton
            onClick={handleAumentarSalarioClick}
            color="#277c1c">
            Aumentar salário(10%)
          </MotionButton>
          <MotionButton
            onClick={handleAniversariosClick}
            color="#277c1c">
            Aniversários
          </MotionButton>
          <MotionButton
            onClick={handleDeleteClick}
            color="red">
            Deletar
          </MotionButton>
      </div>
      <label htmlFor="myInput">{state.labelText}
        <input id="myInput" type="text" disabled={state.inputDisabled} onChange={handleInputChange}/>
        <button disabled={state.buttonDisabled} onClick={handleConfirm}>Confirmar</button>
      </label>      
      <div className="exibicao">
        {Array.isArray(state.responseData) 
          && state.responseData.length > 0 
          && (state.endOfUrl == 'ordenada'
          || state.endOfUrl == '')
          && (
          <table>
            <thead>
              <tr>
                <th>Nome</th>
                <th>Data de Nascimento</th>
                <th>Salário</th>
                <th>Função</th>
              </tr>
            </thead>
            <tbody>
              {state.responseData.map((item, index) => (
                <tr key={index}>
                  <td>{item.nome}</td>
                  <td>{item.dataNascimento}</td>
                  <td>{item.salario}</td>
                  <td>{item.funcao}</td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
        {typeof state.responseData == 'string' 
        && state.responseData !== '...' && (
          <div>{state.responseData}</ div>  
        )}
        {typeof state.responseData == 'string' 
        && state.responseData == '...' && (
            <><Loader /><p>Carregando...</p></>
        )}
        {typeof state.responseData !== 'string'
         && state.endOfUrl === 'mais-velho' && (
          <table>
            <thead>
              <tr>
                <th>Nome</th>
                <th>Idade</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>{state.responseData.nome}</td>
                <td>{state.responseData.idade}</td>
              </tr>
            </tbody>
          </table>
        )}
        {Array.isArray(state.responseData) 
          && state.responseData.length > 0 
          && state.endOfUrl == 'salarios-min'
          && (
          <table>
            <thead>
              <tr>
                <th>Nome</th>
                <th>Salários Mínimos</th>
              </tr>
            </thead>
            <tbody>
              {state.responseData.map((item, index) => (
                <tr key={index}>
                  <td>{item.nome}</td>
                  <td>{item.salarios}</td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
        { typeof state.responseData !== 'string'
          && state.endOfUrl === 'funcoes' && (
            Object.entries(state.responseData).map(([role, people]) => (
              <div
                className='div-funcao' 
                key={role}>
                <h2>{role}</h2>
                <table>
                  <thead>
                    <tr>
                      <th>Nome</th>
                      <th>Data de Nascimento</th>
                      <th>Salário</th>
                    </tr>
                  </thead>
                  <tbody>
                    {people.map((person, index) => (
                      <tr key={index}>
                        <td>{person.nome}</td>
                        <td>{person.dataNascimento}</td>
                        <td>{person.salario}</td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </div>
            ))  
          )
        }
      </div>
    </>
  )
}

export default App
