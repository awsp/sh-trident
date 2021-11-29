<script>
  import PlaybackControls from './PlaybackControls.svelte';
  import Toolbar from './Toolbar.svelte';
  import {generateEpisode} from '../../helpers/manager_helper';

  // Local constants
  const COMPONENT = 'ProgramTable';

  // Local variables
  let debug = true;
  let controls = false;
  let totalEpisode = 0;

  // Exported variables
  export let data = [];
  $: totalEpisodes = generateEpisode(totalEpisode, data);

  const defaultRow = {
    ep: data.length + 1,
    summary: '',
    remarks: ''
  };

  let newRow = false;
  let row = {
    ep: 0,
    summary: '',
    remarks: ''
  };


  function addRow() {
    newRow = true;
  }

  function submit(e) {
    data = [...data, row];
    row = defaultRow;
  }

  function showControls() {
    controls = !controls;
  }

  function updateTotalEpisode(event) {
    console.log(event.target.value);
  }
</script>

<style lang="scss">
  .flex button {
    margin-right: 10px;
    font-size: 16px;

    &.disabled {
      color: #bbb;
      cursor: not-allowed;
      pointer-events: none;
    }
  }

  input[type=text], input[type=number] {
    background: rgba(255, 255, 255, 0.5);
    outline: none;
  }

  .row {
    background: rgba(255, 255, 255, 0.3);
  }

  .row:nth-child(2n) {
    background: rgba(255, 255, 255, 0.6);
  }

  .episode-wrapper {
    input[type="text"] {
      border-radius: 100px;
      background: rgba(255, 255, 255, 0.5);
      border-bottom: 1px solid #ddd;
      text-align: center;
    }
  }

  .undone {
    div.summary, div.ep {
      color: #aaa;
    }
  }

  $radius: 6px;
  .button-set {
    display: flex;

    button {
      background: linear-gradient(to bottom, rgba(238, 238, 238, 0.5) 0%, rgba(255, 255, 255, 0.6) 100%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
      padding: 10px 16px;
      margin: 0;
      border: 0;
      box-shadow: 0 0 2px 0 #eee;
    }

    button:first-child {
      border-top-left-radius: $radius;
      border-bottom-left-radius: $radius;
    }

    button:last-child {
      border-top-right-radius: $radius;
      border-bottom-right-radius: $radius;
    }
  }
</style>

<!-- HTML -->

<div class:debug>
  <header class="metadata">
    {COMPONENT}
  </header>

  <Toolbar />

  <div class="-mx-3 mb-10 bg-white bg-opacity-30 backdrop-filter backdrop-blur-2xl">
    <div class="header flex py-2 px-3">
      <div class="text-xs w-6">#</div>
      <div class="uppercase flex-1 text-xs text-black">Summary</div>
    </div>
    <div class="content">
      {#each totalEpisodes as entry}
        <div class="row bg-opacity-30 px-3 py-3" class:undone={!entry.done}>
          <div class="flex">
            <div class="ep w-6 text-sm">{entry.ep}</div>
            <div class="summary text-sm flex-1">
              <a href="#" on:click={showControls}>{entry.summary}</a>
            </div>
            <div class="text-sm">{entry.done}</div>
          </div>
          {#if controls}
            <PlaybackControls/>
          {/if}
        </div>
      {/each}
    </div>
  </div>
</div>