export const state = () => ({
    token: '',
    username: '',
    userId: '',
    role: ''
  })
  
  export const getters = {
    getToken(state) {
      return state.token
    },
    getUsername(state) {
      return state.username
    },
    getUserId(state) {
      return state.userId
    },
    getRole(state) {
      return state.role
    }
  }
  
  export const mutations = {
    setToken(state, token) {
      state.token = token
    },
    setUsername(state, username) {
      return state.username = username
    },
    setUserId(state, userId) {
      return state.userId = userId
    },
    setRole(state, role) {
      return state.role = role
    }
  }