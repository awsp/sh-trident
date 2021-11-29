<script>
  import {metatags} from '@roxi/routify'
  import Loading from '../components/Loading.svelte';

  metatags.title = 'FLT-MB'
  metatags.description = ''

  let tags = [];
  let tag = '';

  const addTag = (e) => {
    e.preventDefault();
    if (tag !== '') {
      tags = [...tags, tag];
      tag = '';
    }
  };
</script>

<div id="notes">
  FLT

  <ol>
    <li>One time password generator</li>
    <li>Cast assistance</li>
    <li>Bookmark manager</li>
    <li>QR generator</li>
    <li>Secret+Password manager</li>
    <li>Session recorder</li>
    <li>SC to TC converter</li>
    <li>Multi function calculator</li>
    <li>Sudoku solver</li>
  </ol>
</div>

<main>
  <div class="tag-wrapper">
    <form on:submit={addTag}>
      <input type="text" bind:value={tag}/>
      <button type="submit" on:click={addTag}>Add</button>
    </form>

    <ul>
      {#each tags as tag}
        <li>{tag}</li>
      {/each}
    </ul>
  </div>
  <button class="v1">Test</button>
  <button class="v2">Test</button>
</main>

<style lang="scss">
  main {
    width: 100%;
    height: 100%;
    margin: auto;
    background: #fdf9f4;
  }

  div#notes {
    display: none;
  }

  div.tag-wrapper {
    ul {
      display: flex;
      padding: 10px;

      li {
        cursor: default;
        display: inline-block;
        margin: 0 3px;
        padding: 3px 10px;
        background: rgba(255, 255, 255, 0.9);
        border-radius: 4px;
        border: 1px solid #ccc;
        box-shadow: 0 2px 5px 1px rgba(232, 232, 232, 0.8);

        background: linear-gradient(to bottom, #ffffff 0%, #f6f6f6 47%, #ededed 100%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
      }
    }
  }

  button.v1 {
    border: 0;
    background: url('/v1.svg') no-repeat;
    background-size: cover;
    width: 150px;
    height: 34px;
  }

  button.v2 {
    border: 0;
    background: url('/v2.svg') no-repeat;
    background-size: cover;
    width: 150px;
    height: 64px;
  }
</style>