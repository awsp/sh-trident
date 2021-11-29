<script>
  // Local constants
  const COMPONENT = 'Toolbar';

  // Local variables
  let debug = true;
  let totalEpisode = 0;

  // Exported variables

  // Computed variables

  // Local functions
  function setEpisode(event) {
    event.preventDefault(); 
    console.log(totalEpisode);
  }
</script>

<style lang="scss">

</style>

<!-- HTML -->
<div class:debug>
  <header class="metadata">Toolbar</header>

  <form class="text-right" on:submit={setEpisode}>
    <input type="text" 
           on:change={event => totalEpisode = event.target.value}
           placeholder="#"
           class="w-8 text-center px-1 py-0 text-base"/>
  </form>
</div>