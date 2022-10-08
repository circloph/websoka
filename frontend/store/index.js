

export const state = () => ({
    token: '',
    username: '',
    userId: '',
    role: '',
    isAuth: false
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
    },
    isAuth(state) {
      return state.isAuth
    }
  }
  
  export const mutations = {
    setToken(state, token) {
      state.token = token
      this.$cookies.set("token", token, {
        path: '/',
        maxAge: 60 * 60 * 24 * 7
      })
    },
    setUsername(state, username) {
      return state.username = username
    },
    setUserId(state, userId) {
      return state.userId = userId
    },
    setRole(state, role) {
      return state.role = role
    },
    setAuth(state, status) {
      return state.isAuth = status
    } 
  }

  export const actions = {
    async fetchCounter({ commit }, data) {
      const ip = await this.$axios.$post('http://localhost:8080/login', data)
        commit('setToken', ip.token )
        commit('setUsername', ip.login )
        commit('setUserId', ip.userId )
        commit('setRole', ip.role )
        commit('setAuth', true )
        localStorage.setItem('token', ip.token)
    }
  }