<template>
  <div id="app">
    <header>
      <h1>Survey Management App</h1>
      <nav>
        <router-link to="/">Home</router-link> |
        <router-link to="/create">New Survey</router-link>
      </nav>
    </header>
    <main>
      <router-view />
    </main>
  </div>
</template>

<style>
header {
  background: #f5f5f5;
  padding: 1rem;
  text-align: center;
}
nav a {
  margin: 0 10px;
}
</style>
