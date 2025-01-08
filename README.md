

**Final Testing and branch and path: with Time**

curl -L   -H "Accept: application/vnd.github+json"   -H "Authorization: Bearer "   -H "X-GitHub-Api-Version: 2022-11-28" 'https://api.github.com/repos/srinuneehaal/obd-sb-ms/commits?sha=dev&path=policies&since=2024-12-29T17:18:48.205742400Z'


curl -L   -H "Accept: application/vnd.github+json"   -H "Authorization: Bearer "   -H "X-GitHub-Api-Version: 2022-11-28"  'https://api.github.com/repos/srinuneehaal/obd-sb-ms/commits/17def25b1d490b7713164f7c3f16687fc6165d85'

**files-->contents_url-->download_url**

**-->contents_url:**

curl -L   -H "Accept: application/vnd.github+json"   -H "Authorization: Bearer "   -H "X-GitHub-Api-Version: 2022-11-28" 'https://api.github.com/repos/srinuneehaal/obd-sb-ms/contents/policies%2Fpolicy.json?ref=17def25b1d490b7713164f7c3f16687fc6165d85'
**-->download_url**

curl -L   -H "Accept: application/vnd.github+json"   -H "Authorization: Bearer "   -H "X-GitHub-Api-Version: 2022-11-28" 'https://raw.githubusercontent.com/srinuneehaal/obd-sb-ms/17def25b1d490b7713164f7c3f16687fc6165d85/policies/policy.json'


**The state of the status.
	Can be one of: error, failure, pending, success
--->Update Status**

curl -L   -H "Accept: application/vnd.github+json"   -H "Authorization: Bearer "   -H "X-GitHub-Api-Version: 2022-11-28" 'https://api.github.com/repos/srinuneehaal/obd-sb-ms/commits/17def25b1d490b7713164f7c3f16687fc6165d85/statuses'
curl -L  -X POST -H "Accept: application/vnd.github+json"   -H "Authorization: Bearer "   -H "X-GitHub-Api-Version: 2022-11-28" 'https://api.github.com/repos/srinuneehaal/obd-sb-ms/statuses/17def25b1d490b7713164f7c3f16687fc6165d85' -d '{"state":"success","target_url":"https://example.com/build/status","description":"The build is in success!","context":"continuous-integration/jenkins"}'
curl -L   -H "Accept: application/vnd.github+json"   -H "Authorization: Bearer "   -H "X-GitHub-Api-Version: 2022-11-28" 'https://api.github.com/repos/srinuneehaal/obd-sb-ms/commits/17def25b1d490b7713164f7c3f16687fc6165d85/statuses'
