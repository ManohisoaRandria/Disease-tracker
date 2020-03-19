/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


 const xhr = new XMLHttpRequest();
var patient;
var patient2=[];

function diagnostic(){
   var analysesDrop = document.getElementById("analysePatient");
   console.log(analysesDrop.value);
    console.log(patient._id);
 const url1="http://localhost:9191/client/test?diag=1&idAnalyse="+analysesDrop.value.split(" ")[0]+"&idPatient="+patient._id;
 xhr.open('GET',url1, true);
 xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
 xhr.send(null);
 xhr.onreadystatechange =function(){
     if (xhr.readyState == 4) {
       if(xhr.status  == 200) {
         let table=document.getElementById('pourcentage');

         let data=JSON.parse(xhr.responseText);
         console.log("darta"+data);
         let haha="<table border=\"1\"><tr><th>Maladie</th><th>Pourcentage</th></tr>";
         for (var i = 0; i < data.length; i++) {
           haha+="<tr><td>"+data[i].maladie+"</td><td>"+data[i].pourcentage+"%</td></tr>";
         }
         haha+="</table>";
         table.innerHTML=haha;
       // console.log(xhr.responseText);
         // setAnalyse(JSON.parse(xhr.responseText));
         // patient=(JSON.parse(xhr.responseText))[0];
       }else{
         console.log("Error code " + xhr.status);
       }
     }
 }
}
function getAnalyseByPatient(){
 let onePatient=document.getElementById('patient');
 console.log(onePatient.value);
 const url="http://localhost:9191/client/test?patient="+onePatient.value+"&getAnalyse=1";
 xhr.open('GET',url, true);
 xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
 xhr.send(null);
 xhr.onreadystatechange =function(){
   if (xhr.readyState == 4) {
     if(xhr.status  == 200) {
     console.log(JSON.parse(xhr.responseText));
       setAnalyse(JSON.parse(xhr.responseText));
       patient=(JSON.parse(xhr.responseText));
     }else{
       console.log("Error code " + xhr.status);
     }
   }
 }

}
function getAnalyseByPatient2(){
 let onePatient=document.getElementById('patient2');
 const url="http://localhost:9191/client/test?patient="+onePatient.value+"&getAnalyse=1";
 xhr.open('GET',url, true);
 xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
 xhr.send(null);
 xhr.onreadystatechange =function(){
   if (xhr.readyState == 4) {
     if(xhr.status  == 200) {
     //  console.log(JSON.parse(xhr.responseText));
     let compteur=0;
     for (var i = 0; i < patient2.length; i++) {
       if((JSON.parse(xhr.responseText))[0]._id==patient2[i]._id)compteur++;
     }
     if(compteur ==0 ){
       patient2.push((JSON.parse(xhr.responseText)));
        setAnalyse2(JSON.parse(xhr.responseText));
     }

     }else{
       console.log("Error code " + xhr.status);
     }
   }
 }

}
document.getElementById('patient').addEventListener('change', function() {
 var analysesDrop = document.getElementById("analysePatient");
 analysesDrop.innerHTML='';
});
document.getElementById('patient2').addEventListener('change', function() {
 var analysesDrop = document.getElementById("analysePatient");
 analysesDrop.innerHTML='';
});
function setAnalyse(json){
 let analyses=json.analyses;
 console.log(json);
 var analysesDrop = document.getElementById("analysePatient");
   analysesDrop.innerHTML='';
           //Add the Options to the DropDownList.
 for (var i = 0; i < analyses.length; i++) {
   var option = document.createElement("OPTION");

 //Set Customer Name in Text part.
   option.innerHTML =analyses[i]._id;

 //Set CustomerId in Value part.
 option.value = ""+i+" "+analyses[i]._id;


 //Add the Option element to DropDownList.
 analysesDrop.options.add(option);
 }
}
function setAnalyse2(json){
 let analyses=json.analyses;
 console.log(json);
 var analysesDrop = document.getElementById("analysePatient2");
   analysesDrop.innerHTML='';
           //Add the Options to the DropDownList.
 for (var i = 0; i < analyses.length; i++) {
   var option = document.createElement("OPTION");

 //Set Customer Name in Text part.
   option.innerHTML = analyses[i]._id;

 //Set CustomerId in Value part.
 option.value = ""+i;

 //Add the Option element to DropDownList.
 analysesDrop.options.add(option);
 }
}
function voirAnalyse() {
    let dropdown = document.getElementById("analysePatient");
    let idanalysedrop = dropdown.value.split(" ")[0];
    marksData.datasets[0].data = [];
    marksData.datasets[0].label=patient.nom+" "+patient.prenom;
    marksData.datasets[0].backgroundColor=patient.analyses[idanalysedrop].color;
     console.log(idanalysedrop);
    for (var i = 0; i < patient.analyses[idanalysedrop].taux.length; i++) {
        marksData.datasets[0].data.push(patient.analyses[idanalysedrop].taux[i].valeur);
    }
    chart.update();
}
function voirAnalyse2() {
    let dropdown = document.getElementById("analysePatient2");
    let idanalysedrop = dropdown.value;
    let pat=patient2[patient2.length-1];

      let donnee=[];
      for (var i = 0; i < patient2[patient2.length-1].analyses[idanalysedrop].taux.length; i++) {
          donnee.push(patient2[patient2.length-1].analyses[idanalysedrop].taux[i].valeur);
      }
      marksData2.datasets.push({
            label: pat.nom+" "+pat.prenom,
            backgroundColor: pat.analyses[idanalysedrop].color,
            pointHitRadius: 25,
            data: donnee
      });
      chart2.update();

}
               var ctx = document.getElementById('myChart').getContext('2d');

          var marksData = {
             labels: ["c1", "c2", "c3","Tahina"],
             datasets: [
                 {
                     label: "",
                     backgroundColor: "",
                     pointHitRadius: 25,
                     data: []
                 }
             ]
         };
         var ctx2 = document.getElementById('myChart2').getContext('2d');

      var marksData2 = {
       labels: ["c1", "c2", "c3","Tahina"],
       datasets: []
      };



         var chart = new Chart(ctx, {
             // The type of chart we want to create
             type: 'radar',
             // The data for our dataset
             data: marksData,
             options: {
                 dragData: true,
                 dragDataRound: 0,

                 onDragEnd: function (e, datasetIndex, index, value) {
                     console.log(datasetIndex, index, value);

                     var analysesDrop = document.getElementById("analysePatient");
                     let idan=analysesDrop.value.split(" ")[0];
                     let idpatient=patient._id;
                      console.log(idpatient, idan, index,value);
                     const url="http://localhost:9191/client/test?update=1&idpatient="+idpatient+"&idAnalyse="+idan+"&idtaux="+index+"&valeur="+value;
                     xhr.open('GET',url, true);
                     xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                     xhr.send(null);
                     xhr.onreadystatechange =function(){
                       if (xhr.readyState == 4) {
                         if(xhr.status  == 200) {

                           console.log("mety");
                         }else{
                           console.log("Error code " + xhr.status);
                         }
                       }
                     }
                 },
                 scale: {
                     ticks: {
                         max: 100,
                         min: 0,
                         stepSize: 10
                     },
                     gridLines: {
                         display: false
                     }
                 },
                 responsive:true,
                 maintainAspectRatio:false,
                 dragOptions: {
                     magnet: {
                         to: Math.round
                     }
                 }
             }
         });
         var chart2 = new Chart(ctx2, {
             // The type of chart we want to create
             type: 'radar',
             // The data for our dataset
             data: marksData2,
             options: {
                 dragData: false,
                 dragDataRound: 0,

                 onDragEnd: function (e, datasetIndex, index, value) {
                     console.log(datasetIndex, index, value);
                 },
                 scale: {
                     ticks: {
                         max: 100,
                         min: 0,
                         stepSize: 10
                     },
                     gridLines: {
                         display: false
                     }
                 },
                 responsive:true,
                 maintainAspectRatio:false,
                 dragOptions: {
                     magnet: {
                         to: Math.round
                     }
                 }
             }
         });
