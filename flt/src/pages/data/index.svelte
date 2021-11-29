<script>
  // Local constants
  import {API_BASE} from '../../config/env';

  const COMPONENT = 'Data';

  // Local variables
  let debug = true;

  // Exported variables

  // Computed variables

  // Local functions
  async function queryProgram(searchQuery) {
    const response = await fetch(`${API_BASE}/query/program?title=${searchQuery}&sort=startDate,desc`);
    const json = await response.json();

    if (response.ok) {
      return json;
    }
    return new Error(json);
  }
</script>

<style lang="scss">

</style>

<!-- HTML -->
<div class:debug class="page">
  <header class="metadata">Data</header>

  <h1>Data</h1>
  {#await queryProgram("a")}

  {:then programs}
    {@debug programs}
  {/await}
  <a href="/data/create-profile">Create Profile</a>
</div>