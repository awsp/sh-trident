<script>
  import Fa from 'svelte-fa';
  import {faTimes, faCog, faMinus, faPlus} from '@fortawesome/free-solid-svg-icons';

  export let data = {};
  export let themes = {
    otome: "bg-gradient-to-r from-purple-200 via-pink-200 to-red-200",
    milky: "bg-gradient-to-r from-slate-50 via-gray-200 to-gray-100"
  };
  export let active = "milky";

  export function closeProfile() {
  }

  let newProfileForm = false;
  let theData = {};

  function addData() {
    programData = [...programData, theData];
  }
</script>

<style lang="scss">
</style>

<!-- HTML -->
<div class="gothic-frame rounded-2xl mx-auto shadow {themes[active]}">
  <header>
    <button on:click={closeProfile}>
      <Fa icon={faTimes}/>
    </button>
  </header>

  <div class="content px-3 pt-2 pb-4">
    <div class="frame mb-3">
      <img src="/i/v4-1.svg" alt="header frame"/>
    </div>

    <h2 class="mx-auto text-center text-2xl pb-4">
      {data?.program?.title}
    </h2>

    <slot name="program-table"/>
    <slot name="data-accordion"/>
  </div>
</div>