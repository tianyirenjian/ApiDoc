<template>
  <v-app>
    <v-app-bar
        app
    >
      <v-app-bar-nav-icon @click="drawer = true" v-if="showDrawer"></v-app-bar-nav-icon>
      <v-toolbar-title>文档中心</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-switch
          v-model="$vuetify.theme.dark"
          hide-details
          label="深色模式"
      ></v-switch>
    </v-app-bar>
    <v-navigation-drawer
        v-model="drawer"
        absolute
        temporary
    >
      <v-list
          nav
          dense
      >
        <v-list-item-group
            v-model="group"
        >
          <v-list-item>
            <v-list-item-icon>
              <v-icon>mdi-home</v-icon>
            </v-list-item-icon>
            <v-list-item-title>Home</v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-icon>
              <v-icon>mdi-account</v-icon>
            </v-list-item-icon>
            <v-list-item-title>Account</v-list-item-title>
          </v-list-item>

        </v-list-item-group>
      </v-list>
    </v-navigation-drawer>
    <v-main app>
      <v-container fluid>
        <router-view></router-view>
      </v-container>
    </v-main>
    <v-footer app>
      ©2020 tianyisoft.com
    </v-footer>
  </v-app>
</template>
<script>
export default {
  data() {
    return {
      drawer: false,
      group: 1
    }
  },
  computed: {
    showDrawer() {
      return this.$route.name === 'project';
    }
  }
}
</script>