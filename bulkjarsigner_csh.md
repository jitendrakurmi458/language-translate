# Introduction #

In build.xml you will see a reference to a script call bulkjarsigner.csh.  This script will sign all the jar files in the dist directory after -post-jar ANT task fires.

# Usage #

Pretty simple.  Just cut/paste the script below into a folder that is in your path.  For example, ~/bin/.  Then update the set KEYSTORE, STOREPASS, KEYPASS, and ALIAS in the init: block.  Going forward, you will be able to build your Netbeans project and have all the jar files automatically signed.

Since the jar signing is done external to the build.xml, it can be re-used and it makes you code more secure by having your passwords external to the project.

# Script #
```
#!/bin/csh
#
#
init:
   onintr outahere
   set KEYSTORE="~/keystore/my.jks"
   set STOREPASS="mystorepassword"
   set KEYPASS="mykeypass"
   set ALIAS="keyalias"
   set FILELIST=`find ./ -print |grep ".jar"`   

main:
   echo "--- Working out of `pwd` ----"

   foreach ARG ($FILELIST) 
        echo "---- Processing $ARG ----"
        jarsigner -storepass $STOREPASS -keypass $KEYPASS -keystore $KEYSTORE $ARG $ALIAS
        echo " "
   end

   set KEYSTORE=""
   set STOREPASS=""
   set KEYPASS=""
   set ALIAS=""

   goto pissoff

outahere:
   echo " "
   echo "User Exit..."

pissoff:
```

# ANT Target #

Usage with ANT is easy.  Just copy paste the following into your build.xmlL

```
<target name="-post-jar">
   <exec dir="${dist.dir}" executable="bulkjarsigner.csh" os="Linux" />
</target>
```

Granted this just targets linux but it can be adapted for other OS.  Also, _executable_ is assuming that _bulkjarsigner.csh_ is in your path.  If it is not, you will need a fully qualified path to the script.

# Last Thoughts #

This can be done directly in your build.xml using an ant target, however, this approach (for me) is a bit more flexible.  If anything, it is another approach to consider.