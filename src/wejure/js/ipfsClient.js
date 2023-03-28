// not in used when local IPFS node is hosted
import { create } from 'ipfs-http-client'
import GUN from 'gun';
import 'gun/sea';

const projectId = '2NVL3yOfepilUgj4aKhXJph24xw';
const projectSecret = '27e5ccc045de261aa76fbf985b1b375a';
const auth = 'Basic ' + window.btoa(projectId + ':' + projectSecret);
const client = create({
    host: 'ipfs.infura.io',
    port: 5001,
    protocol: 'https',
    apiPath: '/api/v0',
    headers: {
        authorization: auth,
    }
});

export async function add(fileObj) {
    const fileAdded = await client.add(fileObj);
    console.log("Added file:", fileAdded);
}
