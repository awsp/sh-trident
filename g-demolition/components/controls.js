import styles from "../styles/Controls.module.scss";
import {
  Button,
  Classes,
  ControlGroup,
  Dialog, EditableText,
  FormGroup,
  Icon,
  InputGroup,
  Menu,
  MenuItem,
  Popover,
  Position
} from "@blueprintjs/core";
import React, {useState} from "react";
import {KEYS} from "../constants";

export function Controls() {
  const [form, setForm] = useState({name: '', phrases: []});
  const [formDialog, setFormDialog] = useState(false);
  const [newPhrase, setNewPhrase] = useState('');

  const closeDialog = () => {
    setFormDialog(false);
  };

  const openDialog = () => {
    setFormDialog(true);
  };

  const handleChange = (e) => {
    e.preventDefault();
    setForm({
      ...form,
      [e.target.name]: e.target.value,
    });
  };

  const handlePhraseChange = (e) => {
    setNewPhrase(e.target.value);
  };

  const addSearchPhrase = (e) => {
    e.preventDefault();
    if (e.which === KEYS.ENTER || e.button === 0) {
      const phrases = form.phrases;
      if (phrases.indexOf(newPhrase) < 0) {
        phrases.push(newPhrase);
        setForm({
          ...form,
          phrases,
        })
        setNewPhrase('');
      }
    }
  };

  const removePhrase = (index) => {
    const phrases = form.phrases;
    phrases.splice(index, 1);
    setForm({
      ...form,
      phrases,
    });
  };

  const menu = <Menu>
    <MenuItem text="New Focus" onClick={openDialog}/>
  </Menu>;

  return (<section className={styles.controls}>
    <Popover content={menu} position={Position.BOTTOM}>
      <Button icon="plus" text=" Actions " className="bp3-small"/>
    </Popover>

    <Dialog isOpen={formDialog} title="New Focus">
      <div className={styles.dialogForm}>
        <FormGroup inline={true} label="Name">
          <input type="text" className="bp3-input" placeholder="Give focus a name. "
                 id="input-title" name="name" onChange={handleChange} autoComplete="off"/>
        </FormGroup>

        <h3>Phrases</h3>
        <div className={styles.phraseList}>
          {
            form.phrases.length > 0 ? form.phrases.map((phrase, index) => (
              <ControlGroup key={`ph-${index}`} className={styles.phraseItem}>
                <InputGroup placeholder="Enter keywords" value={phrase} disabled={true}/>
                <Button onClick={removePhrase} icon="cross"/>
              </ControlGroup>
            )) : <div className={styles.emptyList}>Please enter at least 1 phrase. </div>
          }

        </div>

        <ControlGroup key="something">
          <InputGroup leftElement={<Icon icon="tag"/>} placeholder="New Search Phrase" fill value={newPhrase}
                      onChange={handlePhraseChange} onKeyUp={addSearchPhrase} name="newPhrase" key="new-search-phrase" autoComplete="off"/>
          <Button icon="plus" onClick={addSearchPhrase} disabled={newPhrase === ''}/>
        </ControlGroup>
      </div>
      <div className={Classes.DIALOG_FOOTER}>
        <div className={styles.itemGroup}>
          <Button intent="primary" disabled={form.name === '' || form.phrases.length === 0}>Save Focus</Button>
          <Button onClick={closeDialog}>Cancel</Button>
        </div>
      </div>
    </Dialog>
  </section>);
}