<script>
  import {createEventDispatcher} from 'svelte';
  import {API_BASE} from '../../config/env';

  export let programs = [];
  const dispatch = createEventDispatcher();

  async function selectProfile(program) {
    const response = await fetch(`${API_BASE}/profile`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        aliasName: program.title,
        program
      })
    });
    const profile = await response.json();
    dispatch('select-profile', {program, profile});
  }

  function closeProfile() {
    selectProfile(null);
  }
</script>

<style lang="scss">

</style>

<!-- HTML -->
<div class="table">
  <header>
    <h2>Table name</h2>
  </header>

  <div class="body">
    <table>
      <thead>
      <tr>
        <th>Name</th>
        <th>Genre</th>
        <th>Start Date</th>
      </tr>
      </thead>
      <tbody>
      {#each programs as program}
        <tr>
          <td>{program.title}</td>
          <td>{program.programGenre}</td>
          <td>{program.startDate}</td>
          <td>
            <a on:click={() => selectProfile(program)}>Create Profile</a>
          </td>
        </tr>
      {/each}
      </tbody>
    </table>
  </div>

  <footer></footer>
</div>