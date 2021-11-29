<script>
  import {createEventDispatcher} from 'svelte';
  import Fa from 'svelte-fa';
  import {faTimes, faCog, faPen} from '@fortawesome/free-solid-svg-icons';

  const dispatch = createEventDispatcher();
  export let editing = false;
  export let data = {};

  function submit() {

  }

  function edit() {
    dispatch('edit', {dataEntryId: data.id});
  }
</script>

<style lang="scss">
  input[type=text] {
    background: rgba(255, 255, 255, 0.4);
    padding: 3px 10px;
    border-radius: 6px;
    box-shadow: 0 0 2px 0 rgba(200, 200, 200, 0.7);
  }

  section, section * {
    transition: all 0.1s;
  }
</style>

<section class="rounded-lg px-3 py-2 mb-2 shadow bg-white bg-opacity-30">
  <header class="tags-section leading-4 pb-1 mb-3">
    <button class="close">
      <Fa icon={faTimes} class="duration-200 text-gray-700"/>
    </button>
    <span class="bg-white backdrop-blur-3xl bg-opacity-70 text-xs px-2 py-0.5 rounded-2xl
          shadow-sm">
            {data.oped}&nbsp;{data.division}
          </span>
  </header>
  <div>
    {#if editing}
      <form on:submit={submit}>
        <div class="mb-2">
          <input type="text" bind:value={data.title} placeholder="Title" class="w-full"/>
        </div>
        <div class="mb-2">
          <input type="text" bind:value={data.artist} placeholder="Artist" class="w-full"/>
        </div>
      </form>
    {:else}
      <h3 class="pb-0.5 text-lg text-gray-900">{data.title}</h3>
      <h5 class="text-xs text-gray-700">{data.artist}</h5>
    {/if}
  </div>
  <footer class="bg-white bg-opacity-40 backdrop-blur backdrop-filter -mx-3 -mb-2
        rounded-b-lg px-3 py-1 mt-3 text-sm ">
    <button type="button" class="mr-2 p-2" on:click={edit}>
      <Fa icon={faPen}/>
    </button>
    <button type="button" class="mr-2 p-2">
      <Fa icon={faCog}/>
    </button>
  </footer>
</section>