<script>
  import Fa from 'svelte-fa';
  import {faPlay, faStop, faFastBackward, faFastForward} from '@fortawesome/free-solid-svg-icons';
</script>

<style lang="scss">

</style>

<!-- HTML -->
<div class="mb-2 episode-wrapper flex">
  <div class="text-left flex-1 button-set">
    <button>
      <Fa icon={faPlay} class="text-sm"/>
    </button>
    <button>
      <Fa icon={faStop} class="text-sm"/>
    </button>
    <button>
      <Fa icon={faFastForward} class="text-sm"/>
    </button>
    <button>
      <Fa icon={faFastBackward} class="text-sm"/>
    </button>
  </div>
</div>