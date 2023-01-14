export const state = () => ({
    userId: '',
    firstName: '',
    lastName: '',
    login: '',
    status: '',
    biography: '',
    role: '',
    token: '',
    isAuth: false,
    content: [],
  })
  
  export const getters = {
    getToken(state) {
      return state.token
    },
    getFirstName(state) {
      return state.firstName
    },
    getUserId: state => {
      return state.userId
    },
    getRole(state) {
      return state.role
    },
    isAuth(state) {
      return state.isAuth
    },
    getLastName(state) {
      return state.lastName
    },
    getStatus(state) {
      return state.status
    },
    getBiography(state) {
      return state.biography
    },
    getLogin(state) {
      return state.login
    },
    getContent(state) {
      return state.content
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
    setFirstName(state, firstName) {
      return state.firstName = firstName
    },
    setUserId(state, userId) {
      return state.userId = userId
    },
    setRole(state, role) {
      return state.role = role
    },
    setAuth(state, isAuth) {
      return state.isAuth = isAuth
    },
    setLastName(state, lastName) {
      return state.lastName = lastName
    },
    setStatus(state, status) {
      return state.status = status
    },
    setBiography(state, biography) {
      return state.biography = biography
    }, 
    setLogin(state, login) {
      return state.login = login
    },
    setContent(state, content) {
      return state.content = content
    },    
  }

  export const actions = {
    async fetchCounter({ commit }, data) {
      const ip = await this.$axios.$post('http://localhost:8080/login', data)
        commit('setToken', ip.token )
        commit('setFirstName', ip.firstName )
        commit('setLogin', ip.login )
        commit('setUserId', ip.userId )
        commit('setRole', ip.role )
        commit('setAuth', true )
        commit('setLastName', ip.lastName )
        commit('setStatus', ip.status )
        commit('setBiography', ip.biography )
        localStorage.setItem('token', ip.token)
    },
    async registerUser({commit}, data) {
      const returnedData = await this.$axios.$post('http://localhost:8080/registration', data)
      commit('setLogin', returnedData.login)
    },
    async getProfile({commit}, data) {
      console.log("In store")
      console.log(data)
      const profile = await this.$axios.$get('http://localhost:8080/users/' + data.id, { headers: {
        'Authorization': "Bearer_" + localStorage.getItem("token")
      }})
      console.log("tut ------------------------------------------------------")
      console.log(profile)
      commit('setFirstName', profile.firstName )
      commit('setLogin', profile.login )
      commit('setUserId', profile.userId )
      commit('setRole', profile.role )
      commit('setAuth', true )
      commit('setLastName', profile.lastName )
      commit('setStatus', profile.status )
      commit('setBiography', profile.biography )
    },
    async save({commit}, data) {
      const returnedData = await this.$axios.$put("http://localhost:8080/users/", data, { headers: {
        'Authorization': "Bearer_" + localStorage.getItem("token")
      }})
      commit('setFirstName', returnedData.firstName )
      commit('setLastName', returnedData.lastName )
      commit('setStatus', returnedData.status )
      commit('setBiography', returnedData.biography )
      commit('setLogin', returnedData.login )
      commit('setUserId', returnedData.userId )
      commit('setRole', returnedData.role )
      commit('setAuth', true )
    },
    async addImage({commit}, data) {
      const returnedData = await this.$axios.$post("http://localhost:8080/upload", data.formData, { headers: {
        'Authorization': "Bearer_" + localStorage.getItem("token")
      }})
    },
    async getMessages({commit}, data) {
      console.log("IN store get messages")
      console.log(data.params.id)
      const returnedData = await this.$axios.$get('http://localhost:8080/messages', { params: { chatId: data.params.id }})
      console.log("getMessages")
      console.log(returnedData)
      commit('setContent', returnedData )
    },
}